package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.entities.ApartmentEntity;


public class ApartmentBuilder {

    private ApartmentBuilder() {
    }

    public static PostApartmentDTO topostApartmentDTO(ApartmentEntity apartment) {
        PostApartmentDTO postApartmentDTO = new PostApartmentDTO(
                apartment.getIdApartment(),
                apartment.getTitle(),
                apartment.getDescription(),
                apartment.getArea(),
                apartment.getPricePerNight(),
                apartment.getCountry(),
                apartment.getFloor(),
                apartment.getStreet(),
                apartment.getCity(),
                apartment.getZipcode(),
                apartment.getGuests(),
                apartment.getBedrooms(),
                apartment.getBathrooms(),
                apartment.isTv(),
                apartment.isWifi(),
                apartment.isKitchen(),
                apartment.isWasher(),
                apartment.isAir_conditioning(),
                apartment.isPool(),
                apartment.isHot_tub(),
                apartment.isBBQ_grill(),
                apartment.isPool_table(),
                apartment.isIndoor_fireplace(),
                apartment.isPiano(),
                apartment.isBalcony(),
                apartment.isTerrace(),
                apartment.isGarden_view(),
                apartment.isSki_out(),
                apartment.isLake_access(),
                apartment.isBeach_access(),
                apartment.isPetsAllowed(),
                apartment.isSmokingAllowed(),
                apartment.isPartiesAllowed(),
                apartment.getCheckIn(),
                apartment.getCheckOut(),
                apartment.isAvailability(),
                apartment.getImagesFolderUrl()
        );
        return postApartmentDTO;
    }

    public static ApartmentEntity toApartmentEntity(PostApartmentDTO postApartmentDTO) {
        ApartmentEntity apartment = new ApartmentEntity();
        apartment.setTitle(postApartmentDTO.getTitle());
        apartment.setDescription(postApartmentDTO.getDescription());
        apartment.setArea(postApartmentDTO.getArea());
        apartment.setPricePerNight(postApartmentDTO.getPricePerNight());
        apartment.setCountry(postApartmentDTO.getCountry());
        apartment.setFloor(postApartmentDTO.getFloor());
        apartment.setStreet(postApartmentDTO.getStreet());
        apartment.setCity(postApartmentDTO.getCity());
        apartment.setZipcode(postApartmentDTO.getZipcode());
        apartment.setGuests(postApartmentDTO.getGuests());
        apartment.setBedrooms(postApartmentDTO.getBedrooms());
        apartment.setBathrooms(postApartmentDTO.getBathrooms());
        apartment.setTv(postApartmentDTO.isTv());
        apartment.setWifi(postApartmentDTO.isWifi());
        apartment.setKitchen(postApartmentDTO.isKitchen());
        apartment.setWasher(postApartmentDTO.isWasher());
        apartment.setAir_conditioning(postApartmentDTO.isAir_conditioning());
        apartment.setPool(postApartmentDTO.isPool());
        apartment.setHot_tub(postApartmentDTO.isHot_tub());
        apartment.setBBQ_grill(postApartmentDTO.isBBQ_grill());
        apartment.setPool_table(postApartmentDTO.isPool_table());
        apartment.setIndoor_fireplace(postApartmentDTO.isIndoor_fireplace());
        apartment.setPiano(postApartmentDTO.isPiano());
        apartment.setBalcony(postApartmentDTO.isBalcony());
        apartment.setTerrace(postApartmentDTO.isTerrace());
        apartment.setGarden_view(postApartmentDTO.isGarden_view());
        apartment.setSki_out(postApartmentDTO.isSki_out());
        apartment.setLake_access(postApartmentDTO.isLake_access());
        apartment.setBeach_access(postApartmentDTO.isBeach_access());
        apartment.setPetsAllowed(postApartmentDTO.isPetsAllowed());
        apartment.setSmokingAllowed(postApartmentDTO.isSmokingAllowed());
        apartment.setPartiesAllowed(postApartmentDTO.isPartiesAllowed());
        apartment.setCheckIn(postApartmentDTO.getCheckIn());
        apartment.setCheckOut(postApartmentDTO.getCheckOut());
        apartment.setAvailability(postApartmentDTO.isAvailability());
        apartment.setImagesFolderUrl(postApartmentDTO.getImagesFolderUrl());
        return apartment;
    }
}
/*

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


}*/