/*
package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.dtos.builders.UserBuilder;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<GetUserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<GetUserDTO> userDTOS = new ArrayList<>();
        for (UserEntity user : users) {
            userDTOS.add(UserBuilder.toGetUserDTO(user));
        }
        return userDTOS;
    }


    public GetUserDTO getUserById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        //List<GetUserDTO> userDTOS = new ArrayList<>();
        if (user.isPresent()) {
            System.out.println("User with id:" + id + " found in database");
            return user.stream().map(UserBuilder::toGetUserDTO).collect(Collectors.toList()).get(0);
        } else {
            System.out.println("User with id:" + id + " not found in database");
            return null;
        }
    }



    public UserEntity createUser(UserEntity user) {
        UserEntity createdUser = userRepository.save(user);
        return createdUser;
    }

    public UpdateUserDTO updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        //Optional<User> user = userRepository.findById(id);
        UserEntity user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            if (updateUserDTO.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
            if (updateUserDTO.getName() != null) user.setName(updateUserDTO.getName());
            if (updateUserDTO.getAddress() != null) user.setAddress(updateUserDTO.getAddress());
            if (updateUserDTO.getEmail() != null) user.setEmail(updateUserDTO.getEmail());
            if (updateUserDTO.getPassword() != null) user.setPassword(updateUserDTO.getPassword());

            UserEntity updatedUser = userRepository.save(user);

            UpdateUserDTO update = new UpdateUserDTO();
            update.setUsername(updatedUser.getUsername());
            update.setName(updatedUser.getName());
            update.setAddress(updatedUser.getAddress());
            update.setEmail(updatedUser.getEmail());
            update.setPassword(updatedUser.getPassword());

            return update;
    }

    public boolean deleteUser(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            System.out.println("User with id:" + id + " deleted from database");
            return true;
        }else{
            System.out.println("User with id:" + id + " not found in database");
            return false;
        }

    }
}
*/