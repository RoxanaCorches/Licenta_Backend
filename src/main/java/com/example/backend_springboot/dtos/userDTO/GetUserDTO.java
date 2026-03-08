package com.example.backend_springboot.dtos.userDTO;

import com.example.backend_springboot.dtos.apartmentDTO.ResponseApartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetUserDTO {
        private UUID idUser;
        private String username;
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private String phoneNumber;
        private String nationality;
        private String city;
        private String address;
        private String zipcode;
        private String blockchainAddress;
        private List<ResponseApartmentDTO> apartmentList;

        public GetUserDTO() {}


}