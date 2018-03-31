package com.natthanan.findmyteacher;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

        String inputText = event.getMessage().getText();
        if (inputText.matches("[-+]?\\d*\\.?\\d+")) {
           return getTeacherFromCourseId(inputText);
        }
        return null;
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    private List<Message> getTeacherFromCourseId(String courseId) {
        List<Message> messages = new ArrayList<>();
        List<Teacher> teachers = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Teacher[]> teacherResponse = restTemplate.exchange("https://find-my-teacher.herokuapp.com/courses/" + courseId, HttpMethod.GET, null, ParameterizedTypeReference.forType(Teacher[].class));
            teachers = Arrays.asList(teacherResponse.getBody());
            for (Teacher teacher :
                    teachers) {
                messages.add(new TextMessage(teacher.getName() + " is at " + teacher.getRoom()));
            }
        } catch (Exception e) {
            messages.add(new TextMessage("Please enter the correct course id"));
        }

        return messages;
    }
}
