package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.apartmentDTO.GetApartmentDTO;
import com.example.backend_springboot.dtos.apartmentDTO.PostApartmentDTO;
import com.example.backend_springboot.services.ApartmentService;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getApartmentById/{id}")
    public GetApartmentDTO getUserById(@PathVariable UUID id){
        GetApartmentDTO apartment = apartmentService.getApartmentById(id);
        return apartment;
    }

    @PostMapping("/createApartment")
    public PostApartmentDTO createApartment(@RequestBody PostApartmentDTO apartment) throws Exception {
        //ApartmentEntity createApartment =  ApartmentBuilder.toApartmentEntity(apartment);
        return apartmentService.createApartment(apartment);
    }
}


/*
    @GetMapping("/getAllApartments")
    public List<ResponseApartmentDTO> getAllApartments(){
        List<ResponseApartmentDTO> apartments = apartmentService.getAllApartments();
        return apartments;
    }

    @GetMapping("/getApartmentById/{id}")
    public ResponseApartmentDTO getUserById(@PathVariable UUID id){
        ResponseApartmentDTO apartments = apartmentService.getApartmentById(id);
        return apartments;
    }
*/
    /*
    @PostMapping("/createApartment")
    public ResponseApartmentDTO createApartment(@RequestBody PostApartmentDTO apartment){
        ApartmentEntity create = apartmentService.createApartment(apartment);
        ResponseApartmentDTO responseApartmentDTO = ApartmentBuilder.toResponseDTO(create);
        return responseApartmentDTO;
    }

    @PutMapping("/updateApartment/{id}")
    public UpdateApartmentDTO updateApartment(@PathVariable UUID id, @RequestBody UpdateApartmentDTO updateApartmentDTO){
        UpdateApartmentDTO update = apartmentService.updateApartment(id, updateApartmentDTO);
        return update;
    }
*/
/*
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

 */
