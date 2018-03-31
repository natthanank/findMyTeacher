package com.natthanan.findmyteacher;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.natthanan.findmyteacher.controller.TeacherController;
import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@LineMessageHandler
public class FindMyTeacherApplication {



    public static void main(String[] args) {
        SpringApplication.run(FindMyTeacherApplication.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        Teacher teacher = null;
        String inputText = event.getMessage().getText();
        if (inputText.startsWith("t00")) {

            RestTemplate restTemplate = new RestTemplate();
            teacher = restTemplate.getForObject("https://find-my-teacher.herokuapp.com/teacher/" + inputText, Teacher.class);

            return new TextMessage(teacher.getName() + " is at " + teacher.getRoom());
        }

        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
