package com.jundat95.sclass.validate;

/**
 * Created by tinhngo on 14/04/2017.
 */
public class MessageDTO {

    public enum MessageType {
        SUCCESS,INFO,WARNING,ERROR
    }

    private String message;
    private MessageType type;

    public MessageDTO() {
    }

    public MessageDTO(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}

