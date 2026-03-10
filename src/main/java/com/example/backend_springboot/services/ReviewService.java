package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.builders.RentalBuilder;
import com.example.backend_springboot.dtos.builders.ReviewBuilder;
import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.entities.*;
import com.example.backend_springboot.repositories.RentalRepository;
import com.example.backend_springboot.repositories.ReviewRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {
    public ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public ReviewService(UserRepository userRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }

    public PostReviewDTO createReview(PostReviewDTO reviewDTO) {
        UserEntity user = userRepository.findById(reviewDTO.getIdUser()).orElseThrow(() ->
                new RuntimeException("User with id:" + reviewDTO.getIdUser() + " not found"));

        RentalEntity rental = rentalRepository.findById(reviewDTO.getIdRental()).orElseThrow(() ->
                new RuntimeException("Rental with id:" + reviewDTO.getIdRental() + " not found"));

        System.out.println("Id user care a facut rent:" + rental.getUser().getIdUser());
        System.out.println("Id user care vrea sa lase review:" + reviewDTO.getIdUser());

        UUID userIdForRental = rental.getUser().getIdUser();
        UUID userIdForReview = reviewDTO.getIdUser();

        if(!rental.getUser().getIdUser().equals(reviewDTO.getIdUser())){
            throw new RuntimeException("User cannot review this rental");
        }

        if(rental.getStatus() != RentalStatus.COMPLETED) {
            throw new RuntimeException("You cannot review this rental");
        }

        //System.out.println("User with id:" + reviewDTO.getIdUser() );
        //System.out.println("Rental with id:" + reviewDTO.getIdRental());

        boolean reviewExists = reviewRepository.existsByRentalIdRental(reviewDTO.getIdRental());

        if(reviewExists){
            throw new RuntimeException("Review already exists for this rental");
        }

        ReviewEntity review = ReviewBuilder.toReviewEntity(reviewDTO);
        review.setUser(user);
        review.setRental(rental);
        System.out.println(review);

        ReviewEntity saveReview = reviewRepository.save(review);
        return ReviewBuilder.toPostReviewDTO(saveReview);
    }
}
