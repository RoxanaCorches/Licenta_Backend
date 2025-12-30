package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.models.User;
import com.example.backend_springboot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<GetUserDTO> getAllUsers(){
        List<GetUserDTO> users = userService.getAllUsers();
        return users;
    }

    /*
    @GetMapping("/getUserById/{id}")
    public GetUserDTO getUserById(@PathVariable UUID id){
        GetUserDTO user = userService.getUserById(id);
        return user;
    }

     */

    @GetMapping("/getUserById/{id}")
    public GetUserDTO getUserById(@PathVariable UUID id){
        GetUserDTO user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        User createUser = userService.createUser(user);
        return createUser;
    }

    @PutMapping("/updateUser/{id}")
    public UpdateUserDTO updateUser(@PathVariable UUID id, @RequestBody UpdateUserDTO updateUserDTO){
        UpdateUserDTO updateUser = userService.updateUser(id, updateUserDTO);
        return updateUser;
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity <Void> deleteUser(@PathVariable UUID id){
        boolean delete = userService.deleteUser(id);
        if(delete){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
