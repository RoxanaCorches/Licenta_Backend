package com.example.backend_springboot.chatbot;

import java.util.UUID;

public record ChatRequest(
        String message,
        UUID apartmentId
) {
}