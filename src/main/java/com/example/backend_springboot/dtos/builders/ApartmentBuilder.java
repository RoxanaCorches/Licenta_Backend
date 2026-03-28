package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.ResponseApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.entities.ApartmentEntity;


public class ApartmentBuilder {

    private ApartmentBuilder() {}

    public static GetApartmentDTO toGetApartmentDTO(ApartmentEntity apartment) {
        GetApartmentDTO getApartmentDTO = new GetApartmentDTO(
                apartment.getUser().getBlockchainAddress(),
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
                apartment.isAirConditioning(),
                apartment.isPool(),
                apartment.isHotTub(),
                apartment.isBbqGrill(),
                apartment.isPoolTable(),
                apartment.isIndoorFireplace(),
                apartment.isPiano(),
                apartment.isBalcony(),
                apartment.isTerrace(),
                apartment.isGardenView(),
                apartment.isSkiOut(),
                apartment.isLakeAccess(),
                apartment.isBeachAccess(),
                apartment.isPetsAllowed(),
                apartment.isSmokingAllowed(),
                apartment.isPartiesAllowed(),
                apartment.getCheckInFrom(),
                apartment.getCheckInUntil(),
                apartment.getCheckOutFrom(),
                apartment.getCheckOutUntil(),
                apartment.isAvailability(),
                apartment.getImageMain(),
                apartment.getImage1(),
                apartment.getImage2(),
                apartment.getImage3(),
                apartment.getImage4(),
                apartment.getMetadataUrl(),
                apartment.getTokenId()

        );
        return getApartmentDTO;
    }

    public static ResponseApartmentDTO toResponseApartmentDTO(ApartmentEntity apartment) {
        ResponseApartmentDTO responseApartmentDTO = new ResponseApartmentDTO(
                apartment.getTitle(),
                apartment.getDescription(),
                apartment.getArea(),
                apartment.getPricePerNight(),
                apartment.getCountry(),
                apartment.getFloor(),
                apartment.getStreet(),
                apartment.getCity(),
                apartment.getGuests(),
                apartment.getBedrooms(),
                apartment.getBathrooms(),
                apartment.getCheckInFrom(),
                apartment.getCheckInUntil(),
                apartment.getCheckOutFrom(),
                apartment.getCheckOutUntil(),
                apartment.getImageMain(),
                apartment.getImage1(),
                apartment.getImage2(),
                apartment.getImage3(),
                apartment.getImage4(),
                apartment.getMetadataUrl(),
                apartment.getTokenId(),
                apartment.getIdApartment()
        );
        return responseApartmentDTO;
    }

    public static PostApartmentDTO topostApartmentDTO(ApartmentEntity apartment) {
        PostApartmentDTO postApartmentDTO = new PostApartmentDTO(
                apartment.getIdApartment(),
                apartment.getUser().getBlockchainAddress(),
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
                apartment.isAirConditioning(),
                apartment.isPool(),
                apartment.isHotTub(),
                apartment.isBbqGrill(),
                apartment.isPoolTable(),
                apartment.isIndoorFireplace(),
                apartment.isPiano(),
                apartment.isBalcony(),
                apartment.isTerrace(),
                apartment.isGardenView(),
                apartment.isSkiOut(),
                apartment.isLakeAccess(),
                apartment.isBeachAccess(),
                apartment.isPetsAllowed(),
                apartment.isSmokingAllowed(),
                apartment.isPartiesAllowed(),
                apartment.getCheckInFrom(),
                apartment.getCheckInUntil(),
                apartment.getCheckOutFrom(),
                apartment.getCheckOutUntil(),
                apartment.getImageMain(),
                apartment.getImage1(),
                apartment.getImage2(),
                apartment.getImage3(),
                apartment.getImage4(),
                apartment.getMetadataUrl(),
                apartment.getTokenId()
        );
        return postApartmentDTO;
    }

    public static UpdateApartmentDTO toUpdateApartmentDTO(ApartmentEntity apartment) {
        UpdateApartmentDTO updateApartmentDTO = new UpdateApartmentDTO(
                apartment.getPricePerNight(),
                apartment.getCheckInFrom(),
                apartment.getCheckInUntil(),
                apartment.getCheckOutFrom(),
                apartment.getCheckOutUntil()
        );
        return updateApartmentDTO;
    }

    public static ApartmentEntity toApartmentEntity(PostApartmentDTO postApartmentDTO) {
        ApartmentEntity apartment = new ApartmentEntity();
        apartment.setIdApartment(postApartmentDTO.getIdApartment());
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
        apartment.setAirConditioning(postApartmentDTO.isAirConditioning());
        apartment.setPool(postApartmentDTO.isPool());
        apartment.setHotTub(postApartmentDTO.isHotTub());
        apartment.setBbqGrill(postApartmentDTO.isBbqGrill());
        apartment.setPoolTable(postApartmentDTO.isPoolTable());
        apartment.setIndoorFireplace(postApartmentDTO.isIndoorFireplace());
        apartment.setPiano(postApartmentDTO.isPiano());
        apartment.setBalcony(postApartmentDTO.isBalcony());
        apartment.setTerrace(postApartmentDTO.isTerrace());
        apartment.setGardenView(postApartmentDTO.isGardenView());
        apartment.setSkiOut(postApartmentDTO.isSkiOut());
        apartment.setLakeAccess(postApartmentDTO.isLakeAccess());
        apartment.setBeachAccess(postApartmentDTO.isBeachAccess());
        apartment.setPetsAllowed(postApartmentDTO.isPetsAllowed());
        apartment.setSmokingAllowed(postApartmentDTO.isSmokingAllowed());
        apartment.setPartiesAllowed(postApartmentDTO.isPartiesAllowed());
        apartment.setCheckInFrom(postApartmentDTO.getCheckInFrom());
        apartment.setCheckInUntil(postApartmentDTO.getCheckInUntil());
        apartment.setCheckOutFrom(postApartmentDTO.getCheckOutFrom());
        apartment.setCheckOutUntil(postApartmentDTO.getCheckOutUntil());

        apartment.setImageMain(postApartmentDTO.getImageMain());
        apartment.setImage1(postApartmentDTO.getImage1());
        apartment.setImage2(postApartmentDTO.getImage2());
        apartment.setImage3(postApartmentDTO.getImage3());
        apartment.setImage4(postApartmentDTO.getImage4());
        apartment.setMetadataUrl(postApartmentDTO.getMetadataUrl());
        apartment.setTokenId(postApartmentDTO.getTokenId());
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