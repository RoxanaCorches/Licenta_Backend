package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdateApartmentDTO {
    private Double pricePerNight;
    private LocalTime checkInFrom;
    private LocalTime checkInUntil;
    private LocalTime checkOutFrom;
    private LocalTime checkOutUntil;

    public UpdateApartmentDTO() {}
}

