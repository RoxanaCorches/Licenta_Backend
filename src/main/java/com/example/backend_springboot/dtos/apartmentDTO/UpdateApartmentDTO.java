package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateApartmentDTO {
    private String description;
    private Double pricePerNight;
    //private boolean availability;
    private String image;

    public UpdateApartmentDTO() {}
}

