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
import org.springframework.stereotype.Service;

import java.util.List;
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
        System.out.println(("Idul userului care are proprietatea pt care se lasa review:" + rental.getApartment().getUser().getIdUser()));

        if(!userIdForRental.equals(userIdForReview)){
            throw new RuntimeException("User cannot review this rental");
        }

        if(rental.getStatus() != RentalStatus.COMPLETED) {
            throw new RuntimeException("You cannot review this rental, rental is uncompleted");
        }

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
