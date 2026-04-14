package com.example.backend_springboot.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatBotService {
    private final Map<List<String>, String> rules = new HashMap<>();


    public ChatBotService() {
        rules.put(List.of("hello", "hi"), "How can I help you?");
        rules.put(List.of("payment", "history", "Where to pay?"), "To make a payment, you need to connect your wallet to the platform. Once connected, you will be able to complete transactions securely.");
        rules.put(List.of("my rent", "booking", "I can't find my booking"), "You can only view your reservations after connecting your wallet. After logging in, you will find them in the ‘My Rentals’ section of your account.");
        rules.put(List.of("cancel", "How do I cancel a rent?"), "A reservation can only be canceled if the request is made at least one day before the check-in date.");
        rules.put(List.of("error", "bug", "issue"), "Ups! Something went wrong. Please try again in a few moments.");
        rules.put(List.of("property", "apartment", "details", "description"), "You can explore all available properties directly from the main page or by accessing the dedicated section.");
        rules.put(List.of(
                "How do I connect my MetaMask wallet?", "wallet","connect"),
                "To connect your MetaMask wallet, you need to connect your wallet to the platform. You need to approve the connection to your MetaMask wallet.");

        rules.put(List.of("review", "add review", "How do I add a review?"), "You can add a review only after your check-out is completed.");
    }

    public String matchRule(String message) {
        if (message == null || message.isEmpty()) {
            return "You must enter a valid message.";
        }

        String response = message.toLowerCase();

        for (var rule : rules.entrySet()) {
            for (String word : rule.getKey()) {
                if (response.contains(word)) {
                    return rule.getValue();
                }
            }
        }
        return "Sorry, but I couldn't find any such rule.";
    }
}
