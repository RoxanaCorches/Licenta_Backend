package com.example.backend_springboot.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatBotService {
    private final Map<List<String>, String> rules = new HashMap<>();


    public ChatBotService() {
        rules.put(List.of("hello", "hi"), "How can I help you");
        rules.put(List.of("check-in", "start"), "You can check-in.");
    }

    public String matchRule(String message) {
        if (message == null || message.isEmpty()) {
            return "No message received.";
        }

        String response = message.toLowerCase();

        for (var rule : rules.entrySet()) {
            for (String word : rule.getKey()) {
                if (response.contains(word)) {
                    return rule.getValue();
                }
            }
        }
        return "Sorry, I didn't understand.";
    }
}
