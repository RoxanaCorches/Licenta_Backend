package com.example.backend_springboot.controllers;

import com.example.backend_springboot.services.ChatBotEntity;
import com.example.backend_springboot.services.ChatBotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/support")
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }
    @PostMapping("/chatBot")
    public ChatBotEntity responseChatBot(@RequestBody ChatBotEntity message) {
        String response = chatBotService.matchRule(message.getMessage());
        return new ChatBotEntity(response);

    }
}


