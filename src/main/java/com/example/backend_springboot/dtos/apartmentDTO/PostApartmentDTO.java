package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class PostApartmentDTO {
    private UUID idApartment;
    private String blockchainAddress;
    private String title;
    private String description;
    private Integer area;
    private Double pricePerNight;
    private String country;
    private String floor;
    private String street;
    private String city;
    private Integer zipcode;
    private Integer guests;
    private Integer bedrooms;
    private Integer bathrooms;
    private boolean tv;
    private boolean wifi;
    private boolean kitchen;
    private boolean washer;
    private boolean airConditioning;
    private boolean pool;
    private boolean hotTub;
    private boolean bbqGrill;
    private boolean poolTable;
    private boolean indoorFireplace;
    private boolean piano;
    private boolean balcony;
    private boolean terrace;
    private boolean gardenView;
    private boolean skiOut;
    private boolean lakeAccess;
    private boolean beachAccess;
    private boolean petsAllowed;
    private boolean smokingAllowed;
    private boolean partiesAllowed;

    private LocalTime checkInFrom;
    private LocalTime checkInUntil;
    private LocalTime checkOutFrom;
    private LocalTime checkOutUntil;

    private String imageMain;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String metadataUrl;
    private String tokenId;


    public PostApartmentDTO() {}



}
