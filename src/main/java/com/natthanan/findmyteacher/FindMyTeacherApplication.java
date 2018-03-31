package com.natthanan.findmyteacher;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.natthanan.findmyteacher.controller.TeacherController;
import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@LineMessageHandler
public class FindMyTeacherApplication {



    public static void main(String[] args) {
        SpringApplication.run(FindMyTeacherApplication.class, args);
    }

    @EventMapping
    public List<Message> handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        ArrayList<Teacher> teachers = null;
        String inputText = event.getMessage().getText();
        List<Message> messages = new ArrayList<>();
        if (inputText.startsWith("0601")) {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Teacher>> teacherResponse = restTemplate.exchange("https://find-my-teacher.herokuapp.com/courses/" + inputText, HttpMethod.GET, null, ParameterizedTypeReference.forType(Teacher.class));
            teachers = (ArrayList<Teacher>) teacherResponse.getBody();
            for (Teacher teacher :
                    teachers) {
                messages.add(new TextMessage(teacher.getName() + " is at " + teacher.getRoom()));
            }

        }
        return messages;
//
//        System.out.println("event: " + event);
//        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
