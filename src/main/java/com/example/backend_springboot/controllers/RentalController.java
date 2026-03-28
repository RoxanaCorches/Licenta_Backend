package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.rentalDTO.GetRentalDTO;
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
    public List<GetRentalDTO> getAllRentalsForUser(@PathVariable UUID id, @PathVariable(required = false) RentalStatus rentalStatus) {
        List<GetRentalDTO> rental = rentalService.getAllRentalsForUser(id, rentalStatus);
        return rental;
    }

    /*
    @GetMapping("/getRentalById/{id}")
    public GetRentalDTO getRentalById(@PathVariable UUID id){
        GetRentalDTO rental = rentalService.getRentalById(id);
        return rental;
    }
*/
    @PostMapping("/createRental")
    public CreateRentalDTO createRental(@RequestBody CreateRentalDTO rentalDTO) throws Exception {
        return rentalService.createRental(rentalDTO);
    }

    @PutMapping("/cancelRental/{id}")
    public GetRentalDTO cancelRental(@PathVariable UUID id) throws Exception {
        return rentalService.cancelRental(id);
    }
}
