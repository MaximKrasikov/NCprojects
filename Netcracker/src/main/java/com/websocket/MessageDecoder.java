package com.websocket;

import com.forms.ChatMessage;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Admin on 13.03.2019.
 */
public class MessageDecoder implements Decoder.Text<ChatMessage> {
    @Override
    public ChatMessage decode(String s) throws DecodeException {
        Gson gson = new Gson();
        ChatMessage message = gson.fromJson(s, ChatMessage.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}