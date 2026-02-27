package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.entities.UserEntity;

public class UserBuilder {
    private UserBuilder() {}

    public static GetUserDTO toGetUserDTO(UserEntity user) {
        GetUserDTO getUserDTO = new GetUserDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getAge(),
                user.getApartments());

        return getUserDTO;
    }

    public static UpdateUserDTO toUpdateUserDTO(UserEntity user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                user.getUsername(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword());
        return updateUserDTO;
    }
}
