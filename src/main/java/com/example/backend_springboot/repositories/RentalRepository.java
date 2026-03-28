package com.example.backend_springboot.repositories;

import com.example.backend_springboot.entities.RentalEntity;
import com.example.backend_springboot.entities.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<RentalEntity, UUID> {
    Optional<RentalEntity> findByIdRental(UUID idRental);
    List<RentalEntity> findByUserIdUserAndStatus(UUID idUser, RentalStatus status);
}
