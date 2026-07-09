package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.builders.UserBuilder;
import com.example.backend_springboot.dtos.userDTO.CreateUserDTO;
import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserById/{walletAddress}")
    public GetUserDTO getUserById(@PathVariable String walletAddress){
        GetUserDTO user = userService.getUserByWalletAddress(walletAddress);
        return user;
    }

    @PostMapping("/createUser")
    public CreateUserDTO createUser(@RequestBody CreateUserDTO userDTO) throws Exception {
        UserEntity user = UserBuilder.toUserEntity(userDTO);
        return userService.mintKycForUser(user); 
    }

    @PutMapping("/updateUser/{id}")
    public UpdateUserDTO updateUser(@PathVariable UUID id, @RequestBody UpdateUserDTO updateUserDTO){
        UpdateUserDTO updateUser = userService.updateUser(id, updateUserDTO);
        return updateUser;
    }

    @PatchMapping("/updateImageProfile/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws Exception {
        userService.uploadImageProfile(id, file);
        return ResponseEntity.ok("Image profile updated");
    }
}
