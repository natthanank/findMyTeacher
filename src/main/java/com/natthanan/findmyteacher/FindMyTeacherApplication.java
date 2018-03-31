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

        Iterable<Teacher> teachers = null;
        String inputText = event.getMessage().getText();
        if (inputText.startsWith("0601")) {
            TeacherController teacherController = new TeacherController();
            teachers = teacherController.getAllTeachers();
            String result = "";
            for (Teacher teacher: teachers) {
                result += teacher.getName() + " tel." + teacher.getTel() + "\n";
            }
            return new TextMessage(result);
        }

        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
