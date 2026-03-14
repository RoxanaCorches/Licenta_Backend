package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetApartmentDTO {
    private String blockchainAddress;
    private UUID idApartment;
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
    private boolean air_conditioning;
    private boolean pool;
    private boolean hot_tub;
    private boolean BBQ_grill;
    private boolean pool_table;
    private boolean indoor_fireplace;
    private boolean piano;
    private boolean balcony;
    private boolean terrace;
    private boolean garden_view;
    private boolean ski_out;
    private boolean lake_access;
    private boolean beach_access;
    private boolean petsAllowed;
    private boolean smokingAllowed;
    private boolean partiesAllowed;
    private LocalTime checkInFrom;
    private LocalTime checkInUntil;
    private LocalTime checkOutFrom;
    private LocalTime checkOutUntil;
    private boolean availability;

    private String imageMain;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    public GetApartmentDTO() {}
}
