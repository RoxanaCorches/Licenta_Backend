package com.example.backend_springboot.dtos.apartmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceConvertRequestDTO {
    Double pricePerNight;
}
