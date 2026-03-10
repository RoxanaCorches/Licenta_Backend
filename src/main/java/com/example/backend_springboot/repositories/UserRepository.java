package com.example.backend_springboot.repositories;

import com.example.backend_springboot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
   List<UserEntity> findByFirstName(String firstName);

    Optional<UserEntity> findByBlockchainAddress(String blockchainAddress);
    Optional<UserEntity> findByIdUser(UUID id);
}
