package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PostApartmentDTO {
    private String name;
    private String location;
    private String description;
    private Double pricePerNight;
    private boolean availability;
    private String image;
    private UUID idOwner;

    public PostApartmentDTO() {}
}
