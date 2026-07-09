package com.example.backend_springboot.dtos.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewForUserDTO {
    private UUID idReview;
    private LocalDate date;
    private String comment;
    private Integer rating;
}
