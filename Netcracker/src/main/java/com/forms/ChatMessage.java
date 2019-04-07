package com.forms;

import lombok.Data;

/**
 * Created by Admin on 13.03.2019.
 */
@Data
public class ChatMessage {
    private String sender;
    private String to;
    private String content;
    private MessageType type;

    public ChatMessage(String sender, String content) {
        this.sender= sender;
        this.content= content;
    }
    public ChatMessage(){

    }

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
