package com.example.backend_springboot.chatbot;

import com.example.backend_springboot.entities.ApartmentEntity;
import com.example.backend_springboot.repositories.ApartmentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiChatService {

    private static final int MAX_APARTMENTS_IN_CONTEXT = 30;

    private final ApartmentRepository apartmentRepository;
    private final AiPromptBuilder aiPromptBuilder;
    private final GeminiClient geminiClient;

    public AiChatService(
            ApartmentRepository apartmentRepository,
            AiPromptBuilder aiPromptBuilder,
            GeminiClient geminiClient
    ) {
        this.apartmentRepository = apartmentRepository;
        this.aiPromptBuilder = aiPromptBuilder;
        this.geminiClient = geminiClient;
    }

    public String generateAnswer(ChatRequest request) {
        System.out.println("AI CHAT SERVICE CALLED");
        System.out.println("Message: " + request.message());
        System.out.println("Apartment ID: " + request.apartmentId());

        if (request.message() == null || request.message().isBlank()) {
            return "Te rog introdu o întrebare despre apartamente.";
        }

        List<ApartmentAiContext> apartments = loadApartments(request);

        System.out.println("Apartments loaded: " + apartments.size());

        String prompt = aiPromptBuilder.buildPrompt(
                request,
                apartments
        );

        System.out.println("Prompt generated:");
        System.out.println(prompt);

        String answer = geminiClient.ask(prompt);

        System.out.println("AI answer:");
        System.out.println(answer);

        return answer;
    }

    private List<ApartmentAiContext> loadApartments(ChatRequest request) {
        if (request.apartmentId() != null) {
            return apartmentRepository.findById(request.apartmentId())
                    .map(apartment -> List.of(mapApartmentToAiContext(apartment)))
                    .orElse(List.of());
        }

        return apartmentRepository.findAll(PageRequest.of(0, MAX_APARTMENTS_IN_CONTEXT))
                .getContent()
                .stream()
                .map(this::mapApartmentToAiContext)
                .toList();
    }


    private ApartmentAiContext mapApartmentToAiContext(ApartmentEntity apartment) {
        return new ApartmentAiContext(
                apartment.getIdApartment(),
                apartment.getTitle(),
                apartment.getCountry(),
                apartment.getCity(),
                apartment.getPricePerNight() != null
                        ? apartment.getPricePerNight().toString()
                        : "necunoscut",
                apartment.getDescription(),
                apartment.isPetsAllowed(),
                apartment.isSmokingAllowed(),
                apartment.isPartiesAllowed()
        );
    }
}