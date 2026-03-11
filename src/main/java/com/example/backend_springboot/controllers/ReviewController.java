package com.example.backend_springboot.controllers;

import com.example.backend_springboot.dtos.reviewDTO.GetReviewForApartmentDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForPropertiesUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForUserDTO;
import com.example.backend_springboot.dtos.reviewDTO.PostReviewDTO;
import com.example.backend_springboot.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getReviewForUser/{id}")
    public List<GetReviewForUserDTO> getReviewForUser(@PathVariable UUID id) {
        List<GetReviewForUserDTO> reviews = reviewService.getAllReviewForUser(id);
        return reviews;
    }

    @GetMapping("/getReviewForPropertiesUser/{id}")
    public List<GetReviewForPropertiesUserDTO> getReviewForPropertiesUser(@PathVariable UUID id) {
        List<GetReviewForPropertiesUserDTO> reviews = reviewService.getAllReviewForPropertiesUser(id);
        return reviews;
    }

    @GetMapping("/getReviewForApartment/{id}")
    public List<GetReviewForApartmentDTO> getReviewForApartment(@PathVariable UUID id) {
        List<GetReviewForApartmentDTO> reviews = reviewService.getAllReviewForApartment(id);
        return reviews;
    }

    @PostMapping("/createReview")
    public PostReviewDTO createReview(@RequestBody PostReviewDTO reviewDTO) throws Exception {
        return reviewService.createReview(reviewDTO);
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        boolean deleted = reviewService.deleteReview(id);
        System.out.println(deleted);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
