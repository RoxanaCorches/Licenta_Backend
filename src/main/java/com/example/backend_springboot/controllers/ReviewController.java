package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.rentalDTO.CreateRentalDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.services.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createReview")
    public PostReviewDTO createReview(@RequestBody PostReviewDTO reviewDTO) throws Exception {
        return reviewService.createReview(reviewDTO);
    }

}
