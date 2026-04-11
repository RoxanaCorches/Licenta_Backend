package com.example.backend_springboot.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ChatBotEntity {
   // private UUID idUser;
    private String message;

    private ChatBotEntity() {}
    public ChatBotEntity(String message) {
        this.message = message;
    }


}
