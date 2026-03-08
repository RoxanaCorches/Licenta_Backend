package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.apartmentDTO.ResponseApartmentDTO;
import com.example.backend_springboot.dtos.userDTO.CreateUserDTO;
import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserBuilder {
    private UserBuilder() {}

    public static GetUserDTO toGetUserDTO(UserEntity user) {
        List<ResponseApartmentDTO> apartments = user.getApartments().stream().map(ApartmentBuilder::toResponseApartmentDTO).collect(Collectors.toList());

        GetUserDTO getUserDTO = new GetUserDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getNationality(),
                user.getCity(),
                user.getAddress(),
                user.getZipcode(),
                user.getBlockchainAddress(),
                apartments

        );
        return getUserDTO;
    }

    public static CreateUserDTO toCreateUserDTO(UserEntity user) {
        CreateUserDTO createUserDTO = new CreateUserDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getNationality(),
                user.getCity(),
                user.getAddress(),
                user.getZipcode(),
                user.getBlockchainAddress()
        );
        return createUserDTO;
    }

    public static UpdateUserDTO toUpdateUserDTO(UserEntity user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getNationality(),
                user.getCity(),
                user.getAddress(),
                user.getZipcode()
        );
        return updateUserDTO;
    }

    public static UserEntity toUserEntity(CreateUserDTO createUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDTO.getUsername());
        userEntity.setFirstName(createUserDTO.getFirstName());
        userEntity.setLastName(createUserDTO.getLastName());
        userEntity.setBirthday(createUserDTO.getBirthday());
        userEntity.setPhoneNumber(createUserDTO.getPhoneNumber());
        userEntity.setNationality(createUserDTO.getNationality());
        userEntity.setCity(createUserDTO.getCity());
        userEntity.setAddress(createUserDTO.getAddress());
        userEntity.setZipcode(createUserDTO.getZipcode());
        userEntity.setBlockchainAddress(createUserDTO.getBlockchainAddress());
        return userEntity;
    }
}
