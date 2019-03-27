package com.controller;

import com.forms.ChatMessage;
import com.websocket.GreetServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Admin on 25.03.2019.
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    private GreetServer greetServer;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws URISyntaxException, IOException {
        //описать логику взаимодействия с клиентом
        greetServer.getMessage();
        return chatMessage;
    }
    //подписка на тему
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        chatMessage.setFrom("admin");
        headerAccessor.getSessionAttributes().put("username",chatMessage.getFrom());
        return chatMessage;
    }
}

