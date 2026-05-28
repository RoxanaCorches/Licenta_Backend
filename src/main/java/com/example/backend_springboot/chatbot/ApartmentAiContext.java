package com.example.backend_springboot.chatbot;

import java.util.UUID;

public record ApartmentAiContext(
        UUID id,
        String title,
        String country,
        String city,
        String pricePerNight,
        String description,
        boolean petsAllowed,
        boolean smokingAllowed,
        boolean partiesAllowed
) {
}