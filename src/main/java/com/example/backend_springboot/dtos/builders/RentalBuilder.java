package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.GetRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.ResponseRentalDTO;
import com.example.backend_springboot.entities.RentalEntity;


public class RentalBuilder {

    public RentalBuilder() {}

    public static CreateRentalDTO toCreateRentalDTO(RentalEntity rental) {
        CreateRentalDTO createRentalDTO = new CreateRentalDTO(
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotalPrice(),
                rental.getTransactionHash(),
                rental.getRentalIdContract(),
                rental.getCreateRental(),
                rental.getUser().getIdUser(),
                rental.getUser().getFirstName(),
                rental.getUser().getLastName(),
                rental.getUser().getPhoneNumber(),
                rental.getApartment().getIdApartment()
        );
        return createRentalDTO;
    }

    public static GetRentalDTO toGetRentalDTO(RentalEntity rental) {
        GetRentalDTO getRentalDTO = new GetRentalDTO(
                rental.getIdRental(),
                rental.getUser().getIdUser(),
                rental.getApartment().getIdApartment(),
                rental.getApartment().getTokenId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getApartment().getCheckInFrom(),
                rental.getApartment().getCheckInUntil(),
                rental.getApartment().getCheckOutFrom(),
                rental.getApartment().getCheckOutUntil(),
                rental.getCreateRental(),
                rental.getTotalPrice(),
                rental.getApartment().getTitle(),
                rental.getApartment().getCity(),
                rental.getApartment().getCountry(),
                rental.getStatus(),
                rental.getApartment().getImageMain()
        );
        return getRentalDTO;
    }

    public static ResponseRentalDTO toResponseRentalDTO(RentalEntity rental, boolean existReview) {
        ResponseRentalDTO getRentalDTO = new ResponseRentalDTO(
                rental.getIdRental(),
                rental.getUser().getIdUser(),
                rental.getApartment().getIdApartment(),
                rental.getApartment().getTokenId(),
                rental.getRentalIdContract(),
                rental.getStartDate(),
                rental.getEndDate(),
                existReview,
                rental.getApartment().getCheckInFrom(),
                rental.getApartment().getCheckInUntil(),
                rental.getApartment().getCheckOutFrom(),
                rental.getApartment().getCheckOutUntil(),
                rental.getCreateRental(),
                rental.getTotalPrice(),
                rental.getApartment().getTitle(),
                rental.getApartment().getCity(),
                rental.getApartment().getCountry(),
                rental.getStatus(),
                rental.getApartment().getImageMain()
        );
        return getRentalDTO;
    }

    public static RentalEntity toRentalEntity(CreateRentalDTO createRentalDTO) {
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setStartDate(createRentalDTO.getStartDate());
        rentalEntity.setEndDate(createRentalDTO.getEndDate());
        rentalEntity.setTotalPrice(createRentalDTO.getTotalPrice());
        rentalEntity.setTransactionHash(createRentalDTO.getTransactionHash());
        rentalEntity.setRentalIdContract(createRentalDTO.getRentalIdContract());
        rentalEntity.setCreateRental(createRentalDTO.getRentalDate());

        return rentalEntity;
    }
}
