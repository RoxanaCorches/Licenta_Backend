package com.example.backend_springboot.repositories;

import com.example.backend_springboot.entities.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, UUID> {
    public List<ApartmentEntity> findByTitle(String title);
    public List<ApartmentEntity> findByCityAndGuestsAndBedrooms(String city, int guest, int rooms);
}
