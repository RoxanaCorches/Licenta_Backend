package com.example.backend_springboot.dtos.userDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UpdateUserDTO {
    private String username;
    private String name;
    private String address;
    private String email;
    private String password;

    public UpdateUserDTO() {}
}