package com.example.backend_springboot.dtos.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewDTO {
    private UUID idUser;
    private UUID idRental;
    private String comment;
    private LocalDate date;
    private Double rating;

    public PostReviewDTO(){}
}
