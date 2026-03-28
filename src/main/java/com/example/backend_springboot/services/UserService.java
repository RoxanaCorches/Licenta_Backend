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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public List<GetUserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<GetUserDTO> userDTOS = new ArrayList<>();
        for (UserEntity user : users) {
            userDTOS.add(UserBuilder.toGetUserDTO(user));
        }
        return userDTOS;
    }

    public GetUserDTO getUserByWalletAddress(String walletAddress) {
        Optional<UserEntity> userOptional = userRepository.findByBlockchainAddress(walletAddress);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            GetUserDTO userDTO = UserBuilder.toGetUserDTO(user);

            // 1. Luăm review-urile pe care utilizatorul LE-A SCRIS (deja există în user.getReviewList())
            List<GetReviewForPropertiesUserDTO> reviewsGiven = user.getReviews().stream()
                    .map(ReviewBuilder::toGetReviewForPropertiesUserDTO)
                    .collect(Collectors.toList());

            System.out.println(reviewsGiven);

            // 2. Luăm review-urile pe care utilizatorul LE-A PRIMIT (pentru proprietățile lui)
            // Trebuie să apelezi o metodă nouă din ReviewRepository
            List<GetReviewForPropertiesUserDTO> reviewsReceived = reviewRepository
                    .findAllReceivedReviewsByOwnerId(user.getIdUser()).stream()
                    .map(ReviewBuilder::toGetReviewForPropertiesUserDTO)
                    .collect(Collectors.toList());

            // 3. Combinăm cele două liste în DTO
            List<GetReviewForPropertiesUserDTO> allReviews = new ArrayList<>();
            allReviews.addAll(reviewsGiven);
            allReviews.addAll(reviewsReceived);

            userDTO.setReviewList(allReviews);

            return userDTO;
        }

        return null;
    }


    /*
    public GetUserDTO getUserById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            System.out.println("User with id:" + id + " found in database");
            return UserBuilder.toGetUserDTO(user.get());
        } else {
            System.out.println("User with id:" + id + " not found in database");
            return null;
        }
    }
     */

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

    /*
    public CreateUserDTO mintKycForUser(UserEntity user) throws Exception {

        Optional<UserEntity> existingUser =
                userRepository.findByBlockchainAddress(user.getBlockchainAddress());

        if (existingUser.isPresent()) {
            throw new IllegalStateException("User already exists for this wallet");
        }

        boolean hasKyc = kycNftService.hasKycNft(user.getBlockchainAddress());

        if (hasKyc) {
            throw new IllegalStateException("KYC NFT already exists for this wallet");
        }

        user.setStatusKyc(false);

        UserEntity createdUser = userRepository.save(user);

        String transactionHash = kycNftService.mintKycNft(user.getBlockchainAddress());
        System.out.println("Transaction Hash: " + transactionHash);

        createdUser.setStatusKyc(true);

        UserEntity updatedUser = userRepository.save(createdUser);

        return UserBuilder.toCreateUserDTO(updatedUser);
    }

     */



    public UpdateUserDTO updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        //Optional<User> user = userRepository.findById(id);
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


    public boolean deleteUser(UUID id) {
        Optional<UserEntity> user = userRepository.findByIdUser(id);
        System.out.println("User found in database:" + user.isPresent());
        if (user.isPresent()) {
            userRepository.delete(user.get());
            System.out.println("User with id:" + id + " deleted from database");
            return true;
        }else {
            System.out.println("User with id:" + id + " not found in database");
            return false;
        }
    }
}
