package com.example.backend_springboot.repositories;

import com.example.backend_springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByName(String name);

}
