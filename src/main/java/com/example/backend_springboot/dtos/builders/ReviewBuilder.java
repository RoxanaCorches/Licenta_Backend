package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.reviewDTO.GetReviewForApartmentDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForPropertiesUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.entities.ReviewEntity;

public class ReviewBuilder {
    public ReviewBuilder (){}

    public static PostReviewDTO toPostReviewDTO(ReviewEntity review) {
        PostReviewDTO postReviewDTO = new PostReviewDTO(
                review.getUser().getIdUser(),
                review.getRental().getIdRental(),
                review.getComment(),
                review.getDate(),
                review.getRating()
        );
        return postReviewDTO;
    }

    public static GetReviewForUserDTO toGetReviewForUserDTO(ReviewEntity review) {
        GetReviewForUserDTO getReviewDTO = new GetReviewForUserDTO(
                review.getIdReview(),
                review.getDate(),
                review.getComment(),
                review.getRating()
        );
        return getReviewDTO;
    }

    public static GetReviewForPropertiesUserDTO toGetReviewForPropertiesUserDTO(ReviewEntity review) {
        GetReviewForPropertiesUserDTO getReviewDTO = new GetReviewForPropertiesUserDTO(
                review.getIdReview(),
                review.getRental().getIdRental(),
                review.getUser().getIdUser(),
                review.getRental().getApartment().getUser().getIdUser(),
                review.getRental().getUser().getLastName(),
                review.getRental().getUser().getFirstName(),
                review.getRental().getApartment().getTitle(),
                review.getRental().getApartment().getCity(),
                review.getRental().getApartment().getCountry(),
                review.getDate(),
                review.getComment(),
                review.getRating()
        );
        return getReviewDTO;
    }

    public static GetReviewForApartmentDTO toGetReviewForApartmentDTO(ReviewEntity review) {
        GetReviewForApartmentDTO getReviewDTO = new GetReviewForApartmentDTO(
                review.getIdReview(),
                review.getUser().getFirstName(),
                review.getUser().getLastName(),
                review.getDate(),
                review.getComment(),
                review.getRating()
        );
        return getReviewDTO;
    }

    public static ReviewEntity toReviewEntity(PostReviewDTO postReviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setComment(postReviewDTO.getComment());
        reviewEntity.setDate(postReviewDTO.getDate());
        reviewEntity.setRating(postReviewDTO.getRating());
        return reviewEntity;
    }
}
