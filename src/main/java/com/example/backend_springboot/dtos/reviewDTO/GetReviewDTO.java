package com.example.backend_springboot.dtos.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewDTO {
    private String firstName;
    private String lastName;
    private LocalDate date;
    private String comment;
    private Double rating;
}
