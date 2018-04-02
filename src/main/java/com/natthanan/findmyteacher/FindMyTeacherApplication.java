package com.natthanan.findmyteacher;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class FindMyTeacherApplication {


    public static void main(String[] args) {
        SpringApplication.run(FindMyTeacherApplication.class, args);
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        String inputText = event.getMessage().getText();
        // if input is course id, get teacher that take this courses
        if (inputText.matches("[-+]?\\d*\\.?\\d+")) {
            sendPushMessage(event);
        }
        // get userid for push
        event.getSource().getUserId();

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
            for (int i = 0; i < 10; i++) {
                for (Teacher teacher :
                        teachers) {
                    messages.add(new TextMessage(teacher.getName() + " is at " + teacher.getRoom()));
                }
            }
        } catch (Exception e) {
            messages.add(new TextMessage("Please enter the correct course id"));
        }


        return messages;
    }

    private void sendPushMessage(MessageEvent<TextMessageContent> event) {
        final LineMessagingClient client = LineMessagingClient
                .builder("K7AJeM33RfE2AXkEfuSAMYHxp7mEWoeMZMBD/jvwJDVwXwXTdtsbH7ZmWIu0csrlOh1Ec3smWjYnKhRaxlt2f6Aa+17Kftuw3XweTNE1IH69u8eVMy1nGGIq0pRHDzT4BsvX9YCPXfrWegeMtwzaaQdB04t89/1O/w1cDnyilFU=")
                .build();

        List<Message> messages = getTeacherFromCourseId(event.getMessage().getText());
        BotApiResponse botApiResponse;
        for (Message message: messages) {
            PushMessage pushMessage = new PushMessage(
                    event.getSource().getUserId(),
                    message);
            try {
                botApiResponse = client.pushMessage(pushMessage).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return;
            }
        }


    }
}
