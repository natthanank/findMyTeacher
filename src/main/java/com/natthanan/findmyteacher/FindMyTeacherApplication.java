package com.natthanan.findmyteacher;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuArea;
import com.linecorp.bot.model.richmenu.RichMenuBounds;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuResponse;
import com.linecorp.bot.model.richmenu.RichMenuSize;
import com.linecorp.bot.model.richmenu.RichMenu.RichMenuBuilder;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.natthanan.findmyteacher.model.HotLine;
import com.natthanan.findmyteacher.model.MyRichMenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

@SpringBootApplication
@LineMessageHandler
public class FindMyTeacherApplication {


    public static void main(String[] args) {
        SpringApplication.run(FindMyTeacherApplication.class, args);

        RichMenuArea richMenuArea = new RichMenuArea(new RichMenuBounds(0, 0, 400, 270), null);
        List<RichMenuArea> richMenuAreas = new ArrayList();
        richMenuAreas.add(richMenuArea);
        RichMenuBuilder richMenu = RichMenu.builder();
        richMenu.size(new RichMenuSize(400, 270))
            .selected(true)
            .name("Test")
            .chatBarText("06012222")
            .areas(richMenuAreas)
            .build();
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        String inputText = event.getMessage().getText();
        // if input is course id, get teacher that take this courses
        if (inputText.matches("[-+]?\\d*\\.?\\d+")) {
            sendPushMessage(event);
            // get userid for push
            event.getSource().getUserId();
        }

        

    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    private List<Message> getHotLineFromCategory(String category) {
        List<Message> messages = new ArrayList<>();
        List<HotLine> hotLines = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<HotLine[]> hotLineResponse = restTemplate.exchange("https://find-my-teacher.herokuapp.com/hotlines/" + Integer.parseInt(category), HttpMethod.GET, null, ParameterizedTypeReference.forType(HotLine[].class));
            hotLines = Arrays.asList(hotLineResponse.getBody());
            for (int i = 0; i < 10; i++) {
                for (HotLine hotLine :
                        hotLines) {
                    messages.add(new TextMessage(hotLine.getTel()));
                }
            }
        } catch (Exception e) {
            messages.add(new TextMessage("Please enter the correct category"));
        }


        return messages;
    }

    private void sendPushMessage(MessageEvent<TextMessageContent> event) {
        final LineMessagingClient client = LineMessagingClient
                .builder("K7AJeM33RfE2AXkEfuSAMYHxp7mEWoeMZMBD/jvwJDVwXwXTdtsbH7ZmWIu0csrlOh1Ec3smWjYnKhRaxlt2f6Aa+17Kftuw3XweTNE1IH69u8eVMy1nGGIq0pRHDzT4BsvX9YCPXfrWegeMtwzaaQdB04t89/1O/w1cDnyilFU=")
                .build();

        // List<Message> messages = getHotLineFromCategory(event.getMessage().getText());
        // BotApiResponse botApiResponse;
        // for (Message message: messages) {
        //     PushMessage pushMessage = new PushMessage(
        //             event.getSource().getUserId(),
        //             message);
        //     try {
        //         botApiResponse = client.pushMessage(pushMessage).get();
        //     } catch (InterruptedException | ExecutionException e) {
        //         e.printStackTrace();
        //         return;
        //     }
        // }
        try {
            ClassPathResource image = new ClassPathResource("/image/2.jpg");
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            WritableRaster raster = bufferedImage.getRaster();
            DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
            RichMenuIdResponse richMenuResponse = client.createRichMenu(MyRichMenu.getRichMenu()).get();
            client.setRichMenuImage(richMenuResponse.getRichMenuId(), "image/jpeg", data.getData());
            client.linkRichMenuIdToUser(event.getSource().getUserId(), richMenuResponse.getRichMenuId());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        



    }
}
