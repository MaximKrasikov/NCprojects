package com.controller;

import com.forms.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * Created by Admin on 19.03.2019.
 */
@Controller
public class WebSocketController { @MessageMapping("/chat.sendMessage")
@SendTo("/topic/publicChatRoom")
public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws URISyntaxException {

        /*final ChatEndpoint clientEndPoint = new ChatEndpoint(new URI("ws://localhost:8080/chat"));
        clientEndPoint.addMessageHandler(new ChatEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
                String userName = jsonObject.getString("user");
                if (!"bot".equals(userName)) {
                    clientEndPoint.sendMessage(getMessage("Hello " + userName +", How are you?"));
                    // other dirty bot logic goes here.. :)
                }
            }
        });
        */
    return chatMessage;
}
    private static String getMessage(String message) {
        return Json.createObjectBuilder()
                .add("user", "bot")
                .add("message", message)
                .build()
                .toString();
    }

    //подписка на тему
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getFrom());
        return chatMessage;
    }
    @RequestMapping("/chat")
    public String index(HttpServletRequest request, org.springframework.ui.Model model) {
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username", username);
        return "chat";
    }
}

