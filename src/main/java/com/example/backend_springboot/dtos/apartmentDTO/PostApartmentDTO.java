package com.example.backend_springboot.dtos.apartmentDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class PostApartmentDTO {
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
    private LocalDate checkIn;
    private LocalDate checkOut;

    private String imagesFolderUrl;

    public PostApartmentDTO() {}

    public PostApartmentDTO(UUID idApartment, String title, String description, Integer area, Double pricePerNight, String country, String floor, String street, String city, Integer zipcode, Integer guests, Integer bedrooms, Integer bathrooms, boolean tv, boolean wifi, boolean kitchen, boolean washer, boolean air_conditioning, boolean pool, boolean hot_tub, boolean BBQ_grill, boolean pool_table, boolean indoor_fireplace, boolean piano, boolean balcony, boolean terrace, boolean garden_view, boolean ski_out, boolean lake_access, boolean beach_access, boolean petsAllowed, boolean smokingAllowed, boolean partiesAllowed, LocalDate checkIn, LocalDate checkOut, boolean availability, String imagesFolderUrl) {

        this.title = title;
        this.description = description;
        this.area = area;
        this.pricePerNight = pricePerNight;
        this.country = country;
        this.floor = floor;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.guests = guests;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.tv = tv;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.washer = washer;
        this.air_conditioning = air_conditioning;
        this.pool = pool;
        this.hot_tub = hot_tub;
        this.BBQ_grill = BBQ_grill;
        this.pool_table = pool_table;
        this.indoor_fireplace = indoor_fireplace;
        this.piano = piano;
        this.balcony = balcony;
        this.terrace = terrace;
        this.garden_view = garden_view;
        this.ski_out = ski_out;
        this.lake_access = lake_access;
        this.beach_access = beach_access;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.partiesAllowed = partiesAllowed;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.imagesFolderUrl = imagesFolderUrl;
    }
}
