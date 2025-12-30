package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.ResponseApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.dtos.builders.ApartmentBuilder;
import com.example.backend_springboot.models.Apartment;
import com.example.backend_springboot.models.User;
import com.example.backend_springboot.repositories.ApartmentRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    public List<ResponseApartmentDTO> getAllApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        List<ResponseApartmentDTO> apartmentDTOS = new ArrayList<>();
        for (Apartment apartment : apartments) {
            apartmentDTOS.add(ApartmentBuilder.toResponseDTO(apartment));
        }
        return apartmentDTOS;
    }

    public ResponseApartmentDTO getApartmentById(UUID id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            System.out.println("Apartment with id:" + id + " found in database");
            return apartment.stream().map(ApartmentBuilder::toResponseDTO).collect(Collectors.toList()).get(0);
        } else {
            System.out.println("Apartment with id:" + id + " not found in database");
            return null;
        }
    }

    public Apartment createApartment(PostApartmentDTO apartmentDTO) {
        User user = userRepository.findById(apartmentDTO.getIdOwner()).orElseThrow(() ->
        new RuntimeException("User with id:" + apartmentDTO.getIdOwner() + " not found"));

        Apartment apartment = new Apartment();
        apartment.setName(apartmentDTO.getName());
        apartment.setLocation(apartmentDTO.getLocation());
        apartment.setDescription(apartmentDTO.getDescription());
        apartment.setPricePerNight(apartmentDTO.getPricePerNight());
        apartment.setAvailability(apartmentDTO.isAvailability());
        apartment.setImage(apartmentDTO.getImage());
        apartment.setUser(user);

        Apartment savedApartment = apartmentRepository.save(apartment);
        return savedApartment;
    }

    public UpdateApartmentDTO updateApartment(UUID id, UpdateApartmentDTO updateApartmentDTO) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
        if (updateApartmentDTO.getDescription() != null) apartment.setDescription(updateApartmentDTO.getDescription());
        if (updateApartmentDTO.getPricePerNight() != null) apartment.setPricePerNight(updateApartmentDTO.getPricePerNight());
        if (updateApartmentDTO.getImage() != null) apartment.setImage(updateApartmentDTO.getImage());

        Apartment updatedApartment = apartmentRepository.save(apartment);

        UpdateApartmentDTO update = new UpdateApartmentDTO();
        update.setDescription(updatedApartment.getDescription());
        update.setPricePerNight(updatedApartment.getPricePerNight());
        update.setImage(updatedApartment.getImage());

        return update;
    }

    public boolean deleteApartment(UUID id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            apartmentRepository.delete(apartment.get());
            System.out.println("Apartment with id:" + id + " deleted from database");
            return true;
        }else{
            System.out.println("Apartment with id:" + id + " not found in database");
            return false;
        }

    }
}
