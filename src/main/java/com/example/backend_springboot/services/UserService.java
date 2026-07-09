package com.example.backend_springboot.services;

import com.example.backend_springboot.blockchain.services.KycNftService;
import com.example.backend_springboot.dtos.builders.ReviewBuilder;
import com.example.backend_springboot.dtos.reviewDTO.GetReviewForPropertiesUserDTO;
import com.example.backend_springboot.dtos.userDTO.CreateUserDTO;
import com.example.backend_springboot.dtos.userDTO.GetUserDTO;
import com.example.backend_springboot.dtos.builders.UserBuilder;
import com.example.backend_springboot.dtos.userDTO.UpdateUserDTO;
import com.example.backend_springboot.entities.UserEntity;
import com.example.backend_springboot.repositories.ReviewRepository;
import com.example.backend_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KycNftService kycNftService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public UserService(UserRepository userRepository, KycNftService kycNftService, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.kycNftService = kycNftService;
        this.reviewRepository = reviewRepository;
    }

    public GetUserDTO getUserByWalletAddress(String walletAddress) {
        Optional<UserEntity> userOptional = userRepository.findByBlockchainAddress(walletAddress);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            GetUserDTO userDTO = UserBuilder.toGetUserDTO(user);

            //review-urile care apartin utilizatorului, pe care el le a scris
            List<GetReviewForPropertiesUserDTO> reviewsGiven = user.getReviews().stream()
                    .map(ReviewBuilder::toGetReviewForPropertiesUserDTO)
                    .collect(Collectors.toList());


            //review-urile care apartin unei proprietati a utilizatorului, pe care le a primit
            List<GetReviewForPropertiesUserDTO> reviewsReceived = reviewRepository
                    .findAllReceivedReviewsByOwnerId(user.getIdUser()).stream()
                    .map(ReviewBuilder::toGetReviewForPropertiesUserDTO)
                    .collect(Collectors.toList());

            // review-uri primite + oferite
            List<GetReviewForPropertiesUserDTO> reviews = new ArrayList<>();
            reviews.addAll(reviewsGiven);
            reviews.addAll(reviewsReceived);

            userDTO.setReviewList(reviews);

            return userDTO;
        }

        return null;
    }

    public CreateUserDTO mintKycForUser(UserEntity user) throws Exception {
        Optional<UserEntity> userExist = userRepository.findByBlockchainAddress(user.getBlockchainAddress());
        if(userExist.isPresent()) {
            throw new IllegalStateException("User already exists in database!");
        }

        boolean hasKyc = kycNftService.hasKycNft(user.getBlockchainAddress());

        if (hasKyc) {
            throw new IllegalStateException("KYC NFT already exists for this wallet!");
        }
        user.setStatusKyc(false);
        UserEntity createdUser = userRepository.save(user);

        String transactionHash = kycNftService.mintKycNft(user.getBlockchainAddress());
        System.out.println("Transaction Hash: " + transactionHash);

        createdUser.setStatusKyc(true);

        UserEntity updatedUser = userRepository.save(createdUser);

        return UserBuilder.toCreateUserDTO(updatedUser);
    }

    public UpdateUserDTO updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        UserEntity user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            if (updateUserDTO.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
            if (updateUserDTO.getFirstName() != null) user.setFirstName(updateUserDTO.getFirstName());
            if (updateUserDTO.getLastName() != null) user.setLastName(updateUserDTO.getLastName());
            if (updateUserDTO.getPhoneNumber() != null) user.setPhoneNumber(updateUserDTO.getPhoneNumber());
            if (updateUserDTO.getNationality() != null) user.setNationality(updateUserDTO.getNationality());
            if (updateUserDTO.getCity() != null) user.setCity(updateUserDTO.getCity());
            if (updateUserDTO.getAddress() != null) user.setAddress(updateUserDTO.getAddress());
            if (updateUserDTO.getZipcode() != null) user.setZipcode(updateUserDTO.getZipcode());

            UserEntity updatedUser = userRepository.save(user);

            UpdateUserDTO update = new UpdateUserDTO();
            update.setUsername(updatedUser.getUsername());
            update.setFirstName(updatedUser.getFirstName());
            update.setLastName(updatedUser.getLastName());
            update.setPhoneNumber(updatedUser.getPhoneNumber());
            update.setNationality(updatedUser.getNationality());
            update.setCity(updatedUser.getCity());
            update.setAddress(updatedUser.getAddress());
            update.setZipcode(updatedUser.getZipcode());

            return update;
    }

    public void uploadImageProfile(UUID id, MultipartFile image) throws IOException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        if (image != null || image.isEmpty())
            user.setProfileImage(image.getBytes());

        userRepository.save(user);
    }
}
