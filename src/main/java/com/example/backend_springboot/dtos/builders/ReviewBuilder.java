package com.example.backend_springboot.dtos.builders;

import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.entities.RentalEntity;
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

    public static GetReviewDTO toGetReviewDTO(ReviewEntity review) {
        GetReviewDTO getReviewDTO = new GetReviewDTO(
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
