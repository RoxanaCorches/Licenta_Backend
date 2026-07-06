package com.example.backend_springboot.dtos.rentalDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateRentalDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    private String transactionHash;
    private Integer rentalIdContract;
    private LocalDate rentalDate;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UUID apartmentId;

    public CreateRentalDTO() {}
}
