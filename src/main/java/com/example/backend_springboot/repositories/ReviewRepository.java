package com.example.backend_springboot.repositories;

import com.example.backend_springboot.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
