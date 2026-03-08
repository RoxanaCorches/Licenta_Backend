package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResponseApartmentDTO {
        private String title;
        private String description;
        private Integer area;
        private Double pricePerNight;
        private String country;
        private String floor;
        private String street;
        private String city;
        private Integer guests;
        private Integer bedrooms;
        private Integer bathrooms;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String imagesFolderUrl;

        public ResponseApartmentDTO() {}
    }
