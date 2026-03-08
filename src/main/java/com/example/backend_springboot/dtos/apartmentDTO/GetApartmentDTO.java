package com.example.backend_springboot.dtos.apartmentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
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
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean availability;
    private String imagesFolderUrl;

    public GetApartmentDTO() {}
}
