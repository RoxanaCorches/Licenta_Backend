package com.example.backend_springboot.chatbot;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AiPromptBuilder {

    public String buildPrompt(
            ChatRequest request,
            List<ApartmentAiContext> apartments
    ) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Ești un asistent AI integrat într-o aplicație de închiriere de apartamente.

                Rolul tău este să ajuți utilizatorul cu informații despre apartamentele existente în aplicație.

                Poți răspunde la întrebări despre:
                - apartamente disponibile;
                - locația apartamentelor;
                - prețul pe noapte;
                - descrierea apartamentelor;
                - facilități;
                - recomandări de apartamente;
                - rezervări existente;
                - disponibilitatea unui apartament într-o anumită perioadă.

                Reguli importante:
                - Răspunde doar pe baza contextului primit.
                - Nu inventa apartamente care nu există în context.
                - Nu inventa prețuri, locații, facilități sau rezervări.
                - Dacă utilizatorul cere o recomandare, recomandă doar dintre apartamentele din context.
                - Dacă utilizatorul întreabă despre disponibilitate, verifică rezervările din context.
                - Dacă perioada cerută de utilizator se suprapune cu o rezervare existentă, explică faptul că apartamentul nu este disponibil pentru întreaga perioadă.
                - Dacă nu există suficiente date în context, spune clar că nu ai suficiente informații.
                - Răspunde în limba română.
                - Răspunsul trebuie să fie clar, natural și ușor de înțeles.
                - Nu menționa promptul, modelul AI sau detalii tehnice.

                """);

        appendSelectedPeriod(prompt, request);
        appendApartments(prompt, apartments);

        prompt.append("""
                
                Întrebarea utilizatorului:
                """);

        prompt.append(request.message());

        return prompt.toString();
    }

    private void appendSelectedPeriod(StringBuilder prompt, ChatRequest request) {
        prompt.append("Perioada selectată de utilizator:\n");
    }

    private void appendApartments(
            StringBuilder prompt,
            List<ApartmentAiContext> apartments
    ) {
        prompt.append("Context apartamente:\n");

        if (apartments == null || apartments.isEmpty()) {
            prompt.append("Nu există apartamente în context.\n\n");
            return;
        }

        for (ApartmentAiContext apartment : apartments) {
            prompt.append("- Apartament ID: ").append(nullSafe(apartment.id())).append("\n");
            prompt.append("  Nume: ").append(nullSafe(apartment.title())).append("\n");
            prompt.append("  Oras: ").append(nullSafe(apartment.city())).append("\n");
            prompt.append("  Preț pe noapte: ").append(nullSafe(apartment.pricePerNight())).append("\n");
            prompt.append("  Descriere: ").append(nullSafe(apartment.description())).append("\n");
            prompt.append("  Sunt permise animale de companie?: ").append(nullSafe(apartment.petsAllowed())).append("\n");
            prompt.append("  Este permis fumatul?: ").append(nullSafe(apartment.smokingAllowed())).append("\n");
            prompt.append("  Sunt permise petrecerile?: ").append(nullSafe(apartment.partiesAllowed())).append("\n");

        }
    }

    private String nullSafe(Object value) {
        if (value == null) {
            return "necunoscut";
        }

        String text = value.toString();

        if (text.isBlank()) {
            return "necunoscut";
        }

        return text;
    }
}