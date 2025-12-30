package com.example.backend_springboot.repositories;

import com.example.backend_springboot.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {
    public List<Apartment> findByName(String name);
}
