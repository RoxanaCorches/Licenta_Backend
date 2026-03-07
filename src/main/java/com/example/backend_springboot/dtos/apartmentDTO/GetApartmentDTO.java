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
    private UUID idApartment;
    private String title;
    private String description;
    private Integer area;
    private String country;
    private String floor;
    private String street;
    private String city;
    private String zipcode;
    private Integer guests;
    private Integer bedrooms;
    private Integer bathrooms;
    private Boolean petsAllowed;
    private Boolean smokingAllowed;
    private Boolean partiesAllowed;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double pricePerNight;
    private boolean availability;
    private String imagesFolderUrl;

    public GetApartmentDTO() {}
}
