package com.example.backend_springboot.repositories;

import com.example.backend_springboot.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
