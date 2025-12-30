package com.example.backend_springboot.dtos.userDTO;

import com.example.backend_springboot.models.Apartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetUserDTO {
        private UUID idUser;
        private String username;
        private String name;
        private String address;
        private String email;
        private Integer age;
        private List<Apartment> apartmentList;
        //private String blockchainAddress;
        public GetUserDTO() {}

}