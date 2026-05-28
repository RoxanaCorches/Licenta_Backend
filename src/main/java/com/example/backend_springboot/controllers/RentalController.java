package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.GetRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.ResponseRentalDTO;
import com.example.backend_springboot.entities.RentalStatus;
import com.example.backend_springboot.services.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/getAllRentals")
    public List<GetRentalDTO> getAllRentals(){
        List<GetRentalDTO> rentals = rentalService.getAllRentals();
        return rentals;
    }

    @GetMapping("/getRentalsForUserById/{id}")
    public List<ResponseRentalDTO> getAllRentalsForUser(@PathVariable UUID id, @PathVariable(required = false) RentalStatus rentalStatus) {
        List<ResponseRentalDTO> rental = rentalService.getAllRentalsForUser(id, rentalStatus);
        return rental;
    }

    @PostMapping("/createRental")
    public CreateRentalDTO createRental(@RequestBody CreateRentalDTO rentalDTO) throws Exception {
        return rentalService.createRental(rentalDTO);
    }

    @PutMapping("/checkInRental/{idRental}")
    public GetRentalDTO checkInRental(@PathVariable UUID idRental) throws Exception {
        return rentalService.checkInRental(idRental);
    }

    @PutMapping("/checkOutRental/{idRental}")
    public GetRentalDTO checkOutRental(@PathVariable UUID idRental) throws Exception {
        return rentalService.checkOutRental(idRental);
    }

    @PutMapping("/cancelRental/{idRental}")
    public GetRentalDTO cancelRental(@PathVariable UUID idRental) throws Exception {
        return rentalService.cancelRental(idRental);
    }
}
