package com.example.backend_springboot.dtos.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class GetReviewForPropertiesUserDTO {
    private UUID id;
    private String title;
    private String city;
    private String country;
    private LocalDate date;
    private String comment;
    private Double rating;
}
