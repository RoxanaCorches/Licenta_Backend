package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UpdateApartmentDTO {
    private Double pricePerNight;
    private LocalDate checkIn;
    private LocalDate checkOut;
    //private boolean availability;

    public UpdateApartmentDTO() {}
}

