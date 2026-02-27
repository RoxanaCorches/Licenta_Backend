package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.ResponseApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.entities.ApartmentEntity;

public class ApartmentBuilder {

    private ApartmentBuilder() {}

    public static GetApartmentDTO toGetApartmentDTO(ApartmentEntity apartment) {
        GetApartmentDTO getApartmentDTO = new GetApartmentDTO(
                apartment.getIdApartment(),
                apartment.getName(),
                apartment.getLocation(),
                apartment.getDescription(),
                apartment.getPricePerNight(),
                apartment.isAvailability(),
                apartment.getImage()
        );
        return getApartmentDTO;
    }

    public static UpdateApartmentDTO toUpdateApartmentDTO(ApartmentEntity apartment) {
        UpdateApartmentDTO updateApartmentDTO = new UpdateApartmentDTO(
                apartment.getDescription(),
                apartment.getPricePerNight(),
                apartment.getImage()
        );
        return updateApartmentDTO;
    }

    public static ResponseApartmentDTO toResponseDTO(ApartmentEntity apartment) {
        ResponseApartmentDTO responseApartmentDTO = new ResponseApartmentDTO();
        responseApartmentDTO.setIdApartment(apartment.getIdApartment());
        responseApartmentDTO.setName(apartment.getName());
        responseApartmentDTO.setLocation(apartment.getLocation());
        responseApartmentDTO.setDescription(apartment.getDescription());
        responseApartmentDTO.setPricePerNight(apartment.getPricePerNight());
        responseApartmentDTO.setAvailability(apartment.isAvailability());
        responseApartmentDTO.setImage(apartment.getImage());
        responseApartmentDTO.setIdOwner(apartment.getUser().getIdUser()
        );
        return responseApartmentDTO;
    }





}
