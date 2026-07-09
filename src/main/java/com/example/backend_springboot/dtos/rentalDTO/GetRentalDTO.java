package com.example.backend_springboot.dtos.rentalDTO;

import com.example.backend_springboot.entities.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetRentalDTO {
    private UUID rentalId;
    private UUID userId;
    private UUID apartmentId;
    private String tokenId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime checkInFrom;
    private LocalTime checkInUntil;
    private LocalTime checkOutFrom;
    private LocalTime checkOutUntil;
    private LocalDate rentalDate;
    private Double totalPrice;
    private String title;
    private String city;
    private String country;
    private RentalStatus status;
    private String imageMainUrl;

    public GetRentalDTO() {}
}


