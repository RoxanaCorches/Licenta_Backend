package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.UpdateApartmentDTO;
import com.example.backend_springboot.services.ApartmentService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {
    public ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/getAllApartments")
    public List<GetApartmentDTO> getAllApartments(){
        List<GetApartmentDTO> apartments = apartmentService.getAllApartments();
        return apartments;
    }

    @GetMapping("/getFilteredApartments")
    public List<GetApartmentDTO> getFilteredApartments(@RequestParam String location,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                                                       @RequestParam int guests,
                                                       @RequestParam int rooms){
        List<GetApartmentDTO> apartments = apartmentService.getFilteredApartments(location, checkIn, checkOut, guests, rooms);
        return apartments;
    }

    @GetMapping("/getApartmentById/{id}")
    public GetApartmentDTO getUserById(@PathVariable UUID id){
        GetApartmentDTO apartment = apartmentService.getApartmentById(id);
        return apartment;
    }

    @PostMapping(value = "/createApartment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostApartmentDTO createApartment(@RequestPart("information") PostApartmentDTO apartment, @RequestPart("images") List<MultipartFile> images) throws Exception {
        //ApartmentEntity createApartment =  ApartmentBuilder.toApartmentEntity(apartment);
        return apartmentService.createApartment(apartment, images);
    }

    /*
    @PostMapping("/createApartment")
    public PostApartmentDTO createApartment(@RequestBody PostApartmentDTO apartment) throws Exception {
        //ApartmentEntity createApartment =  ApartmentBuilder.toApartmentEntity(apartment);
        return apartmentService.createApartment(apartment);
    }
     */

    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<UpdateApartmentDTO> updateApartment(@PathVariable UUID id, @RequestBody UpdateApartmentDTO updateApartmentDTO){
        UpdateApartmentDTO update = apartmentService.updateApartment(id, updateApartmentDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/deleteApartment/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable UUID id){
        boolean delete = apartmentService.deleteApartment(id);
        if(delete){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
