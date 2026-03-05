package com.example.backend_springboot.dtos.userDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String nationality;
    private String city;
    private String address;
    private String zipcode;
    public UpdateUserDTO() {}
}