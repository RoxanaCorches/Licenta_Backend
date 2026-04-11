package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
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

        private LocalTime checkInFrom;
        private LocalTime checkInUntil;
        private LocalTime checkOutFrom;
        private LocalTime checkOutUntil;

        private String imageMain;
        private byte[] image1;
        private byte[] image2;
        private byte[] image3;
        private byte[] image4;
        private String metadataUrl;
        private String tokenId;
        private UUID idApartment;


        public ResponseApartmentDTO() {}
    }
