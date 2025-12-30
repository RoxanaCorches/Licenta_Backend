package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.models.User;

public class UserBuilder {
    private UserBuilder() {}

    public static GetUserDTO toGetUserDTO(User user) {
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

    public static UpdateUserDTO toUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                user.getUsername(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword());
        return updateUserDTO;
    }
}
