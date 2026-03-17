package com.example.backend_springboot.services;

import com.example.backend_springboot.blockchain.contracts.Marketplace;
import com.example.backend_springboot.blockchain.contracts.PropertyNFT;
import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.dtos.builders.ApartmentBuilder;
import com.example.backend_springboot.entities.ApartmentEntity;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.repositories.ApartmentRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final PinataService pinataService;
    private final PropertyNFT propertyNFT;
    private final Marketplace marketplace;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository, PinataService pinataService, PropertyNFT propertyNFT, Marketplace marketplace) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.pinataService = pinataService;
        this.propertyNFT = propertyNFT;
        this.marketplace = marketplace;
    }

    public List<GetApartmentDTO> getAllApartments() {
        List<ApartmentEntity> apartments = apartmentRepository.findAll();
        List<GetApartmentDTO> apartmentDTOS = new ArrayList<>();
        for (ApartmentEntity apartment : apartments) {
            apartmentDTOS.add(ApartmentBuilder.toGetApartmentDTO(apartment));
        }
        return apartmentDTOS;
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
        System.out.println("User with id:" + apartmentDTO.getBlockchainAddress());

        ApartmentEntity apartment = ApartmentBuilder.toApartmentEntity(apartmentDTO);
        apartment.setUser(user);
        ApartmentEntity savedApartment = apartmentRepository.save(apartment);

        //upload imagine principala pe pinata
        MultipartFile mainImage = images.get(0);
        String nameImageOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_" +
                mainImage.getOriginalFilename();

        String cidMainImage = pinataService.uploadFile(mainImage, nameImageOnPinata);
        savedApartment.setImageMain("https://gateway.pinata.cloud/ipfs/" + cidMainImage);

        //salvare imagini in folder(imaginile secundare ale apartamentului)
        for(int i = 1; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            String nameImage = savedApartment.getIdApartment() + "_" +
                    savedApartment.getTitle().replaceAll("\\s+","_") + "_" +
                    image.getOriginalFilename();
            String pathFile = Paths.get("images/" + nameImage).toString();

            if(i == 1) {  savedApartment.setImage1("/images/" + nameImage);}
            if(i == 2) {  savedApartment.setImage2("/images/" + nameImage);}
            if(i == 3) {  savedApartment.setImage3("/images/" + nameImage);}
            if(i == 4) {  savedApartment.setImage4("/images/" + nameImage);}
            Files.write(Path.of(pathFile), image.getBytes());

        }

        //upload fisier metadata pe pinata
        String metadataJson = generateMetadataFile(savedApartment);

        String metadataFileOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_metadata.json";

        String cidMetadata = pinataService.uploadMetadata(metadataJson.getBytes(StandardCharsets.UTF_8), metadataFileOnPinata);
        String metadataIpfsUrl = "https://gateway.pinata.cloud/ipfs/" + cidMetadata;
        savedApartment.setMetadataUrl(metadataIpfsUrl);

        String walletAddress = apartment.getUser().getBlockchainAddress();
        System.out.println("Wallet Address:" + walletAddress);


        //mint nft si obtinere tokenId

        TransactionReceipt receipt = propertyNFT.mint(walletAddress, metadataIpfsUrl).send();
        BigInteger tokenId = propertyNFT.tokenIdCounter().send();
        savedApartment.setTokenId(tokenId.toString());


        System.out.println(cidMainImage);
        System.out.println(cidMetadata);

        ApartmentEntity finalApartment = apartmentRepository.save(savedApartment);

        PostApartmentDTO response = ApartmentBuilder.topostApartmentDTO(finalApartment);
        response.setMetadataUrl(metadataIpfsUrl);

        return response;

    }

    /*
    public PostApartmentDTO createApartment(PostApartmentDTO apartmentDTO, List<MultipartFile> images) throws Exception {
        UserEntity user = userRepository.findByBlockchainAddress(apartmentDTO.getBlockchainAddress()).orElseThrow(() ->
                new RuntimeException("User with blockchain address:" + apartmentDTO.getBlockchainAddress() + " not found"));
        System.out.println("User with id:" + apartmentDTO.getBlockchainAddress());

        ApartmentEntity apartment = ApartmentBuilder.toApartmentEntity(apartmentDTO);
        apartment.setUser(user);
        ApartmentEntity savedApartment = apartmentRepository.save(apartment);

        //upload imagine principala pe pinata
        MultipartFile mainImage = images.get(0);
        String nameImageOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_" +
                mainImage.getOriginalFilename();

        String cidMainImage = pinataService.uploadFile(mainImage, nameImageOnPinata);
        savedApartment.setImageMain("https://gateway.pinata.cloud/ipfs/" + cidMainImage);

        //salvare imagini in folder(imaginile secundare ale apartamentului)
        for(int i = 1; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            String nameImage = savedApartment.getIdApartment() + "_" +
                    savedApartment.getTitle().replaceAll("\\s+","_") + "_" +
                    image.getOriginalFilename();
            String pathFile = Paths.get("images/" + nameImage).toString();

            if(i == 1) {  savedApartment.setImage1("/images/" + nameImage);}
            if(i == 2) {  savedApartment.setImage2("/images/" + nameImage);}
            if(i == 3) {  savedApartment.setImage3("/images/" + nameImage);}
            if(i == 4) {  savedApartment.setImage4("/images/" + nameImage);}
            Files.write(Path.of(pathFile), image.getBytes());

        }

        //upload fisier metadata pe pinata
        String metadataJson = generateMetadataFile(savedApartment);

        String metadataFileOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_metadata.json";

        String cidMetadata = pinataService.uploadMetadata(metadataJson.getBytes(StandardCharsets.UTF_8), metadataFileOnPinata);
        String metadataIpfsUrl = "https://gateway.pinata.cloud/ipfs/" + cidMetadata;
        savedApartment.setMetadataUrl(metadataIpfsUrl);

        String walletAddress = apartment.getUser().getBlockchainAddress();
        System.out.println("Wallet Address:" + walletAddress);

        //mint nft si obtinere tokenId
        TransactionReceipt receipt = propertyNFT.mint(walletAddress, metadataIpfsUrl).send();
        BigInteger tokenId = propertyNFT.tokenIdCounter().send();
        savedApartment.setTokenId(tokenId.toString());

        String marketplaceAddress = marketplace.getContractAddress();
        propertyNFT.setApprovalForAll(marketplaceAddress, true).send();
        System.out.println("Marketplace address:" + marketplaceAddress);

        LocalTime hoursCheckInUntil = savedApartment.getCheckInUntil();
        LocalTime hoursCheckInFrom = savedApartment.getCheckInFrom();

        //BigInteger hours = BigInteger.valueOf(Duration.between(hoursCheckInUntil, hoursCheckInFrom).toHours());
        long hours = Duration.between(hoursCheckInUntil, hoursCheckInFrom).toHours();
        if(hours < 0 ) {
            hours += 24;
        }

        BigInteger hoursCheckIn = BigInteger.valueOf(hours);
        Double pricePerNight = savedApartment.getPricePerNight();
        BigInteger price = BigDecimal.valueOf(pricePerNight).movePointRight(2).toBigInteger();


        TransactionReceipt receip =  marketplace.listNftOnMarketplace(tokenId, price, hoursCheckIn).send();
        List<Marketplace.NFTListedEventResponse> responses = marketplace.getNFTListedEvents(receip);


        String ownerMarketPlace = String.valueOf(marketplace.ownerMarketplaceAddress());
        System.out.println("ownerMarketPlace address:" + ownerMarketPlace);





        System.out.println(cidMainImage);
        System.out.println(cidMetadata);


        ApartmentEntity savedApartmentFinal = apartmentRepository.save(savedApartment);
        return ApartmentBuilder.topostApartmentDTO(savedApartmentFinal);


    }
*/
    /*
    public PostApartmentDTO createApartment(PostApartmentDTO apartmentDTO, List<MultipartFile> images) throws Exception {
        UserEntity user = userRepository.findByBlockchainAddress(apartmentDTO.getBlockchainAddress()).orElseThrow(() ->
                new RuntimeException("User with blockchain address:" + apartmentDTO.getBlockchainAddress() + " not found"));
        System.out.println("User with id:" + apartmentDTO.getBlockchainAddress());

        ApartmentEntity apartment = ApartmentBuilder.toApartmentEntity(apartmentDTO);
        apartment.setUser(user);
        ApartmentEntity savedApartment = apartmentRepository.save(apartment);

        //upload imagine principala pe pinata
        MultipartFile mainImage = images.get(0);
        String nameImageOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_" +
                mainImage.getOriginalFilename();

        String cidMainImage = pinataService.uploadFile(mainImage, nameImageOnPinata);
        savedApartment.setImageMain("https://gateway.pinata.cloud/ipfs/" + cidMainImage);

        //upload fisier metadata pe pinata
        String metadataJson = generateMetadataFile(savedApartment);

        String metadataFileOnPinata = savedApartment.getIdApartment() + "_" +
                savedApartment.getTitle().replaceAll("\\s+","_") + "_metadata.json";

        String cidMetadata = pinataService.uploadMetadata(metadataJson.getBytes(StandardCharsets.UTF_8), metadataFileOnPinata);
        String metadataIpfsUrl = "https://gateway.pinata.cloud/ipfs/" + cidMetadata;
        savedApartment.setMetadataUrl(metadataIpfsUrl);

        String walletAddress = apartment.getUser().getBlockchainAddress();
        System.out.println("Wallet Address:" + walletAddress);

        TransactionReceipt receipt = propertyNFT.mint(walletAddress, metadataIpfsUrl).send();
        BigInteger tokenId = propertyNFT.tokenIdCounter().send();
        savedApartment.setTokenId(tokenId.toString());

        System.out.println(cidMainImage);
        System.out.println(cidMetadata);


        ApartmentEntity savedApartmentFinal = apartmentRepository.save(savedApartment);
        return ApartmentBuilder.topostApartmentDTO(savedApartmentFinal);
    }

     */


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
        attributes.add(Map.of("trait_type", "Air Conditioning", "value", apartment.isAir_conditioning()));
        attributes.add(Map.of("trait_type", "Pool", "value", apartment.isPool()));
        attributes.add(Map.of("trait_type", "Hot Tub", "value", apartment.isHot_tub()));
        attributes.add(Map.of("trait_type", "BBQ Grill", "value", apartment.isBBQ_grill()));
        attributes.add(Map.of("trait_type", "Pool Table", "value", apartment.isPool_table()));
        attributes.add(Map.of("trait_type", "Indoor Fireplace", "value", apartment.isIndoor_fireplace()));
        attributes.add(Map.of("trait_type", "Piano", "value", apartment.isPiano()));
        attributes.add(Map.of("trait_type", "Balcony", "value", apartment.isBalcony()));
        attributes.add(Map.of("trait_type", "Terrace", "value", apartment.isTerrace()));
        attributes.add(Map.of("trait_type", "Garden View", "value", apartment.isGarden_view()));
        attributes.add(Map.of("trait_type", "Ski-Out", "value", apartment.isSki_out()));
        attributes.add(Map.of("trait_type", "Lake Access", "value", apartment.isLake_access()));
        attributes.add(Map.of("trait_type", "Beach Access", "value", apartment.isBeach_access()));

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









/*
    public List<ResponseApartmentDTO> getAllApartments() {
        List<ApartmentEntity> apartments = apartmentRepository.findAll();
        List<ResponseApartmentDTO> apartmentDTOS = new ArrayList<>();
        for (ApartmentEntity apartment : apartments) {
            apartmentDTOS.add(ApartmentBuilder.toResponseDTO(apartment));
        }
        return apartmentDTOS;
    }

    public ResponseApartmentDTO getApartmentById(UUID id) {
        Optional<ApartmentEntity> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            System.out.println("Apartment with id:" + id + " found in database");
            return apartment.stream().map(ApartmentBuilder::toResponseDTO).collect(Collectors.toList()).get(0);
        } else {
            System.out.println("Apartment with id:" + id + " not found in database");
            return null;
        }
    }
*/
    /*
    public ApartmentEntity createApartment(PostApartmentDTO apartmentDTO) {
        UserEntity user = userRepository.findById(apartmentDTO.getIdOwner()).orElseThrow(() ->
        new RuntimeException("User with id:" + apartmentDTO.getIdOwner() + " not found"));

        ApartmentEntity apartment = new ApartmentEntity();
        apartment.setTitle(apartmentDTO.getName());
        apartment.setLocation(apartmentDTO.getLocation());
        apartment.setDescription(apartmentDTO.getDescription());
        apartment.setPricePerNight(apartmentDTO.getPricePerNight());
        apartment.setAvailability(apartmentDTO.isAvailability());
        apartment.setImage(apartmentDTO.getImage());
        apartment.setUser(user);

        ApartmentEntity savedApartment = apartmentRepository.save(apartment);
        return savedApartment;
    }

    public UpdateApartmentDTO updateApartment(UUID id, UpdateApartmentDTO updateApartmentDTO) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
        if (updateApartmentDTO.getDescription() != null) apartment.setDescription(updateApartmentDTO.getDescription());
        if (updateApartmentDTO.getPricePerNight() != null) apartment.setPricePerNight(updateApartmentDTO.getPricePerNight());
        if (updateApartmentDTO.getImage() != null) apartment.setImage(updateApartmentDTO.getImage());

        ApartmentEntity updatedApartment = apartmentRepository.save(apartment);

        UpdateApartmentDTO update = new UpdateApartmentDTO();
        update.setDescription(updatedApartment.getDescription());
        update.setPricePerNight(updatedApartment.getPricePerNight());
        update.setImage(updatedApartment.getImage());

        return update;
    }
*/
/*
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
    */

