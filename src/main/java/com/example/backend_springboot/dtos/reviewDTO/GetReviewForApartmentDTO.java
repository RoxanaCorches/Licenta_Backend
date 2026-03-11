package com.example.backend_springboot.dtos.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewForApartmentDTO {
    private UUID idReview;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private String comment;
    private Double rating;
}
