package com.natthanan.findmyteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@LineMessageHandler
public class FindMyTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindMyTeacherApplication.class, args);
	}

//	@EventMapping
//	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
//		System.out.println("event: " + event);
//		return new TextMessage(event.getMessage().getText());
//	}
//
//	@EventMapping
//	public void handleDefaultMessageEvent(Event event) {
//		System.out.println("event: " + event);
//	}
}
