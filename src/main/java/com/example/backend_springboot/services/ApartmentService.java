package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.dtos.builders.ApartmentBuilder;
import com.example.backend_springboot.entities.ApartmentEntity;
import com.example.backend_springboot.entities.RentalEntity;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.repositories.ApartmentRepository;
import com.example.backend_springboot.repositories.RentalRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final PinataService pinataService;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository, RentalRepository rentalRepository, PinataService pinataService) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.pinataService = pinataService;
    }

    public List<GetApartmentDTO> getAllApartments() {
        List<ApartmentEntity> apartments = apartmentRepository.findAll();
        List<GetApartmentDTO> apartmentDTOS = new ArrayList<>();
        for (ApartmentEntity apartment : apartments) {
            apartmentDTOS.add(ApartmentBuilder.toGetApartmentDTO(apartment));
        }
        return apartmentDTOS;
    }

    public List <GetApartmentDTO> getFilteredApartments(String location, LocalDate checkIn, LocalDate checkOut, int guests, int rooms) {
        List<ApartmentEntity> apartments = apartmentRepository.findByCityAndGuestsAndBedrooms(location, guests, rooms);
        List<GetApartmentDTO> apartmentsAvailable = new ArrayList<>();

        for(ApartmentEntity apartment : apartments) {
            boolean isRented = false;

            for(RentalEntity rental: apartment.getRentals()) {
                if(rental.getStartDate().isBefore(checkOut) && rental.getEndDate().isAfter(checkIn)) {
                    isRented = true;
                    break;
                }
            }

            if(!isRented) {
                apartmentsAvailable.add(ApartmentBuilder.toGetApartmentDTO(apartment));
            }
        }
        return apartmentsAvailable;
    }

    public GetApartmentDTO getApartmentById(UUID id) {
        Optional<ApartmentEntity> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            System.out.println("Apartment with id:" + id + " found in database");
            return ApartmentBuilder.toGetApartmentDTO(apartment.get());
        } else {
            System.out.println("Apartment with id:" + id + " not found in database");
            return null;
        }
    }

    public PostApartmentDTO createApartment(PostApartmentDTO apartmentDTO, List<MultipartFile> images) throws Exception {
        UserEntity user = userRepository.findByBlockchainAddress(apartmentDTO.getBlockchainAddress()).orElseThrow(() ->
                new RuntimeException("User with blockchain address:" + apartmentDTO.getBlockchainAddress() + " not found"));
        System.out.println("User with blockchain address:" + apartmentDTO.getBlockchainAddress());

        ApartmentEntity apartment;
        if(apartmentDTO.getIdApartment() != null){
            apartment = apartmentRepository.findById(apartmentDTO.getIdApartment())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));
        } else {
            apartment = ApartmentBuilder.toApartmentEntity(apartmentDTO);
            apartment.setUser(user);

            apartment = apartmentRepository.saveAndFlush(apartment);
        }

        System.out.println("Apartment with id:" + apartment.getIdApartment());
        System.out.println("ApartmentDto with id:" + apartmentDTO.getIdApartment());

        //upload imagine principala pe pinata
        MultipartFile mainImage = images.get(0);
        String nameImageOnPinata = apartment.getIdApartment() + "_" +
                apartment.getTitle().replaceAll("\\s+","_") + "_" +
                mainImage.getOriginalFilename();

        System.out.println("Name image on pinata:" + nameImageOnPinata);

        String cidMainImage = pinataService.uploadFile(mainImage, nameImageOnPinata);
        apartment.setImageMain("https://gateway.pinata.cloud/ipfs/" + cidMainImage);


        System.out.println("Number of images received: " + images.size());
        for (MultipartFile file : images) {
            System.out.println(file.getOriginalFilename());
        }

        for(int i = 1; i < images.size(); i++) {
            if (i == 1) {

                apartment.setImage1(images.get(1).getBytes());
            }
            if (i == 2) {
                apartment.setImage2(images.get(2).getBytes());
            }
            if (i == 3) {
                apartment.setImage3(images.get(3).getBytes());
            }
            if (i == 4) {
                apartment.setImage4(images.get(4).getBytes());
            }
        }

        apartmentRepository.save(apartment);
        //upload fisier metadata pe pinata
        String metadataJson = generateMetadataFile(apartment);

        String metadataFileOnPinata = apartment.getIdApartment() + "_" +
                apartment.getTitle().replaceAll("\\s+","_") + "_metadata.json";

        String cidMetadata = pinataService.uploadMetadata(metadataJson.getBytes(StandardCharsets.UTF_8), metadataFileOnPinata);
        String metadataIpfsUrl = "https://gateway.pinata.cloud/ipfs/" + cidMetadata;
        apartment.setMetadataUrl(metadataIpfsUrl);

        String walletAddress = apartment.getUser().getBlockchainAddress();
        System.out.println("Wallet Address:" + walletAddress);

        if(apartmentDTO.getTokenId() != null){
            apartment.setTokenId(apartmentDTO.getTokenId());
        }

        ApartmentEntity finalApartment = apartmentRepository.save(apartment);
        PostApartmentDTO response = ApartmentBuilder.topostApartmentDTO(finalApartment);
        response.setMetadataUrl(metadataIpfsUrl);

        return response;
    }

    public UpdateApartmentDTO updateApartment(UUID id, UpdateApartmentDTO updateApartmentDTO) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
        if (updateApartmentDTO.getPricePerNight() != null) apartment.setPricePerNight(updateApartmentDTO.getPricePerNight());
        if (updateApartmentDTO.getCheckInFrom() != null) apartment.setCheckInFrom(updateApartmentDTO.getCheckInFrom());
        if (updateApartmentDTO.getCheckInUntil() != null) apartment.setCheckInUntil(updateApartmentDTO.getCheckInUntil());
        if (updateApartmentDTO.getCheckOutFrom() != null) apartment.setCheckOutFrom(updateApartmentDTO.getCheckOutFrom());
        if (updateApartmentDTO.getCheckOutUntil() != null) apartment.setCheckOutUntil(updateApartmentDTO.getCheckOutUntil());

        ApartmentEntity updatedApartment = apartmentRepository.save(apartment);

        UpdateApartmentDTO update = new UpdateApartmentDTO();
        update.setPricePerNight(updatedApartment.getPricePerNight());
        update.setCheckInFrom(updatedApartment.getCheckInFrom());
        update.setCheckInUntil(updatedApartment.getCheckInUntil());
        update.setCheckOutFrom(updatedApartment.getCheckOutFrom());
        update.setCheckOutUntil(updatedApartment.getCheckOutUntil());
        return update;
    }

    public boolean deleteApartment(UUID id) {
        Optional<ApartmentEntity> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            apartmentRepository.delete(apartment.get());
            System.out.println("Apartment with id:" + id + " deleted from database");
            return true;
        }else{
            System.out.println("Apartment with id:" + id + " not found in database");
            return false;
        }
    }

    public String generateMetadataFile(ApartmentEntity apartment) {
        Map<String, Object> metadataFile = new HashMap<>();
        metadataFile.put("name", apartment.getTitle());
        metadataFile.put("description", apartment.getDescription());
        metadataFile.put("image", apartment.getImageMain());

        List<Map<String, Object>> attributes = new ArrayList<>();
        attributes.add(Map.of("trait_type", "Area", "value", apartment.getArea()));
        attributes.add(Map.of("trait_type", "Price per night", "value", apartment.getPricePerNight()));
        attributes.add(Map.of("trait_type", "Country", "value", apartment.getCountry()));
        attributes.add(Map.of("trait_type", "Floor", "value", apartment.getFloor()));
        attributes.add(Map.of("trait_type", "Street", "value", apartment.getStreet()));
        attributes.add(Map.of("trait_type", "City", "value", apartment.getCity()));
        attributes.add(Map.of("trait_type", "Zipcode", "value", apartment.getZipcode()));
        attributes.add(Map.of("trait_type", "Guests", "value", apartment.getGuests()));
        attributes.add(Map.of("trait_type", "Bedrooms", "value", apartment.getBedrooms()));
        attributes.add(Map.of("trait_type", "Bathrooms", "value", apartment.getBathrooms()));

        attributes.add(Map.of("trait_type", "TV", "value", apartment.isTv()));
        attributes.add(Map.of("trait_type", "Wifi", "value", apartment.isWifi()));
        attributes.add(Map.of("trait_type", "Kitchen", "value", apartment.isKitchen()));
        attributes.add(Map.of("trait_type", "Kitchen", "value", apartment.isWasher()));
        attributes.add(Map.of("trait_type", "Air Conditioning", "value", apartment.isAirConditioning()));
        attributes.add(Map.of("trait_type", "Pool", "value", apartment.isPool()));
        attributes.add(Map.of("trait_type", "Hot Tub", "value", apartment.isHotTub()));
        attributes.add(Map.of("trait_type", "BBQ Grill", "value", apartment.isBbqGrill()));
        attributes.add(Map.of("trait_type", "Pool Table", "value", apartment.isPoolTable()));
        attributes.add(Map.of("trait_type", "Indoor Fireplace", "value", apartment.isIndoorFireplace()));
        attributes.add(Map.of("trait_type", "Piano", "value", apartment.isPiano()));
        attributes.add(Map.of("trait_type", "Balcony", "value", apartment.isBalcony()));
        attributes.add(Map.of("trait_type", "Terrace", "value", apartment.isTerrace()));
        attributes.add(Map.of("trait_type", "Garden View", "value", apartment.isGardenView()));
        attributes.add(Map.of("trait_type", "Ski-Out", "value", apartment.isSkiOut()));
        attributes.add(Map.of("trait_type", "Lake Access", "value", apartment.isLakeAccess()));
        attributes.add(Map.of("trait_type", "Beach Access", "value", apartment.isBeachAccess()));

        attributes.add(Map.of("trait_type", "Pets allowed?", "value", apartment.isPetsAllowed()));
        attributes.add(Map.of("trait_type", "Smoking allowed?", "value", apartment.isSmokingAllowed()));
        attributes.add(Map.of("trait_type", "Parties or events allowed?", "value", apartment.isPartiesAllowed()));

        attributes.add(Map.of("trait_type", "Check-in From", "value", apartment.getCheckInFrom()));
        attributes.add(Map.of("trait_type", "Check-in Until", "value", apartment.getCheckInUntil()));
        attributes.add(Map.of("trait_type", "Check-out From", "value", apartment.getCheckOutFrom()));
        attributes.add(Map.of("trait_type", "Check-out Until", "value", apartment.getCheckOutUntil()));

        metadataFile.put("attributes", attributes);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(metadataFile);
        return json;
    }
}




