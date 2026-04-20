package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.builders.RentalBuilder;
import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.GetRentalDTO;
import com.example.backend_springboot.entities.ApartmentEntity;
import com.example.backend_springboot.entities.RentalEntity;
import com.example.backend_springboot.entities.RentalStatus;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.repositories.ApartmentRepository;
import com.example.backend_springboot.repositories.RentalRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private ApartmentRepository apartmentRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, ApartmentRepository apartmentRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public List<GetRentalDTO> getAllRentals() {
        List<RentalEntity> rentals = rentalRepository.findAll();
        List<GetRentalDTO> rentalDTOS = new ArrayList<>();
        for (RentalEntity rental : rentals) {
            rentalDTOS.add(RentalBuilder.toGetRentalDTO(rental));
        }
        return rentalDTOS;
    }

    public List<GetRentalDTO> getAllRentalsForUser(UUID userId, RentalStatus rentalStatus) {
        List<RentalEntity> rentals;
        if(rentalStatus != null) {
            rentals = rentalRepository.findByUserIdUserAndStatus(userId, rentalStatus);
        }else {
            rentals = rentalRepository.findAll();
        }
        return rentals.stream().map(RentalBuilder::toGetRentalDTO).collect(Collectors.toList());
    }

    /*
    public GetRentalDTO getRentalById(UUID id) {
        Optional<RentalEntity> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            System.out.println("Rental with id:" + id + " found in database");
            return RentalBuilder.toGetRentalDTO(rental.get());
        } else {
            System.out.println("Rental with id:" + id + " not found in database");
            return null;
        }
    }
     */

    boolean isAvailabilityForRent(UUID idApartment, LocalDate startDate, LocalDate endDate) {
        List<RentalEntity> rentals = rentalRepository.findByApartment_IdApartment(idApartment);
        for(RentalEntity rental : rentals) {
            if(rental.getStatus() == RentalStatus.CANCELLED) {
                continue;
            }

            if(startDate.isBefore(rental.getEndDate()) && endDate.isAfter(rental.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public CreateRentalDTO createRental(CreateRentalDTO rentalDTO) {
        if(!isAvailabilityForRent(rentalDTO.getApartmentId(), rentalDTO.getStartDate(), rentalDTO.getEndDate())) {
            throw new RuntimeException("Rental is not available for this apartment");
        }

        UserEntity user = userRepository.findByIdUser(rentalDTO.getUserId()).orElseGet(() -> {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setIdUser(rentalDTO.getUserId());
                    userEntity.setFirstName(rentalDTO.getFirstName());
                    userEntity.setLastName(rentalDTO.getLastName());
                    userEntity.setPhoneNumber(rentalDTO.getPhoneNumber());
                    return userRepository.save(userEntity);
                });

        ApartmentEntity apartment = apartmentRepository.findById(rentalDTO.getApartmentId()).orElseThrow(() ->
                new RuntimeException("Apartment with id:" + rentalDTO.getApartmentId() + " not found"));

        apartment.setAvailability(false);
        apartmentRepository.save(apartment);

        System.out.println("User with id:" + rentalDTO.getUserId());
        System.out.println("Apartment with id:" + rentalDTO.getApartmentId());

        RentalEntity rental = RentalBuilder.toRentalEntity(rentalDTO);
        rental.setUser(user);
        rental.setApartment(apartment);
        rental.setStatus(RentalStatus.UPCOMING);

        System.out.println(rental);

        RentalEntity savedRental = rentalRepository.save(rental);
        return RentalBuilder.toCreateRentalDTO(savedRental);
    }

    public GetRentalDTO checkInRental(UUID idRental) {
        RentalEntity rental = rentalRepository.findByIdRental(idRental).orElseThrow(() ->
                new RuntimeException("Rental with id:" + rentalRepository.findByIdRental(idRental) + " not found"));
        rental.setStatus(RentalStatus.IN_PROGRESS);
        rentalRepository.save(rental);
        return RentalBuilder.toGetRentalDTO(rental);
    }

    public GetRentalDTO checkOutRental(UUID idRental) {
        RentalEntity rental = rentalRepository.findByIdRental(idRental).orElseThrow(() ->
                new RuntimeException("Rental with id:" + rentalRepository.findByIdRental(idRental) + " not found"));
        rental.setStatus(RentalStatus.COMPLETED);
        rentalRepository.save(rental);
        return RentalBuilder.toGetRentalDTO(rental);
    }

    public GetRentalDTO cancelRental(UUID idRental) throws Exception{
       RentalEntity rental = rentalRepository.findByIdRental(idRental).orElseThrow(() ->
                new RuntimeException("Rental with id:" + rentalRepository.findByIdRental(idRental) + " not found"));

        ApartmentEntity apartment = apartmentRepository.findById(rental.getApartment().getIdApartment()).orElseThrow(() ->
                new RuntimeException("Apartment not found"));

        apartment.setAvailability(false);
        apartmentRepository.save(apartment);

        System.out.println("Rental with id:" + rentalRepository.findByIdRental(idRental));

        rental.setStatus(RentalStatus.CANCELLED);

        System.out.println(rental);

        RentalEntity savedRental = rentalRepository.save(rental);
        return RentalBuilder.toGetRentalDTO(savedRental);
    }

    /*
    public CreateRentalDTO createRental(CreateRentalDTO rentalDTO) {
        UserEntity user = userRepository.findByBlockchainAddress(rentalDTO.getUser().getBlockchainAddress()).orElseThrow(() ->
                new RuntimeException("User with id:" + rentalDTO.getUser().getBlockchainAddress() + " not found"));

        ApartmentEntity apartment = apartmentRepository.findById(rentalDTO.getApartment().getIdApartment()).orElseThrow(() ->
                new RuntimeException("Apartment with id:" + rentalDTO.getApartment().getIdApartment() + " not found"));

        System.out.println("User with id:" + rentalDTO.getUser().getBlockchainAddress());
        System.out.println("Apartment with id:" + rentalDTO.getApartment().getIdApartment());

        RentalEntity rental = RentalBuilder.toRentalEntity(rentalDTO);
        rental.setUser(user);
        rental.setApartment(apartment);

        System.out.println(rental);

        RentalEntity savedRental = rentalRepository.save(rental);
        return RentalBuilder.toCreateRentalDTO(savedRental);
    }

     */

}
