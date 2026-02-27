package com.example.backend_springboot.repositories;

import com.example.backend_springboot.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<RentalEntity, UUID> {

}
