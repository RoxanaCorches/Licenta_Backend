package com.example.backend_springboot.repositories;

import com.example.backend_springboot.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    boolean existsByRentalIdRental(UUID idRental);
    List<ReviewEntity> findByUserIdUser(UUID userId);
    List<ReviewEntity> findByRentalIdRental(UUID idRental);
    List<ReviewEntity> findByRentalApartmentIdApartment(UUID idApartment);

    @Query("SELECT r FROM ReviewEntity r WHERE r.rental.apartment.user.idUser = :ownerId")
    List<ReviewEntity> findAllReceivedReviewsByOwnerId(@Param("ownerId") UUID ownerId);
}
