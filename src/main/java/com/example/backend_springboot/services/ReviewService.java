package com.example.backend_springboot.services;

import com.example.backend_springboot.dtos.builders.ReviewBuilder;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForApartmentDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForPropertiesUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.entities.*;
import com.example.backend_springboot.repositories.RentalRepository;
import com.example.backend_springboot.repositories.ReviewRepository;
import com.example.backend_springboot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    public final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public ReviewService(UserRepository userRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<GetReviewForUserDTO> getAllReviewForUser(UUID userId) {
        List<ReviewEntity> reviews;
            reviews = reviewRepository.findByUserIdUser(userId);
        return reviews.stream().map(ReviewBuilder::toGetReviewForUserDTO).collect(Collectors.toList());
    }

    public List<GetReviewForPropertiesUserDTO> getAllReviewForPropertiesUser(UUID userId) {
        List<ReviewEntity> reviews;
        reviews = reviewRepository.findByUserIdUser(userId);
        return reviews.stream().map(ReviewBuilder::toGetReviewForPropertiesUserDTO).collect(Collectors.toList());
    }

    public List<GetReviewForApartmentDTO> getAllReviewForApartment(UUID apartmentId) {
        List<ReviewEntity> reviews;
        reviews = reviewRepository.findByRentalApartmentIdApartment(apartmentId);
        return reviews.stream().map(ReviewBuilder::toGetReviewForApartmentDTO).collect(Collectors.toList());
    }

    public PostReviewDTO createReview(PostReviewDTO reviewDTO) {
        UserEntity user = userRepository.findById(reviewDTO.getIdUser()).orElseThrow(() ->
                new RuntimeException("User with id:" + reviewDTO.getIdUser() + " not found"));

        RentalEntity rental = rentalRepository.findById(reviewDTO.getIdRental()).orElseThrow(() ->
                new RuntimeException("Rental with id:" + reviewDTO.getIdRental() + " not found"));

        UUID userIdForRental = rental.getUser().getIdUser();
        UUID userIdForReview = reviewDTO.getIdUser();

        System.out.println("Id user care a facut rent:" + userIdForRental);
        System.out.println("Id user care vrea sa lase review:" + userIdForReview);

        if(!userIdForRental.equals(userIdForReview)){
            throw new RuntimeException("User cannot review this rental");
        }

        if(rental.getStatus() != RentalStatus.COMPLETED) {
            throw new RuntimeException("You cannot review this rental, rental is uncompleted");
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

    @Transactional
    public boolean deleteReview(UUID id) {
        Optional<ReviewEntity> review = reviewRepository.findById(id);
        System.out.println("Review with id:" + id);
        System.out.println("Review found in database:" + review.isPresent());

        if (review.isPresent()) {
            RentalEntity rental = review.get().getRental();
            if(rental != null){
                rental.setReview(null);
            }
            reviewRepository.delete(review.get());
            System.out.println("Review with id:" + id + " deleted from database");
            return true;
        }else {
            System.out.println("Review with id:" + id + " not found in database");
            return false;
        }
    }
}
