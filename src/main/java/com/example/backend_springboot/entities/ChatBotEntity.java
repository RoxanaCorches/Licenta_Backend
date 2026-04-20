package com.example.backend_springboot.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatBotEntity {
    private String message;

    private ChatBotEntity() {}
    public ChatBotEntity(String message) {
        this.message = message;
    }
}
