package com.example.backend_springboot.controllers;

import com.example.backend_springboot.blockchain.services.KycNftService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kycNft")
public class KycNftController {

    private final KycNftService kycNftService;

    public KycNftController(KycNftService kycNftService) {
        this.kycNftService = kycNftService;
    }

    @GetMapping("/kyc/status")
    public Map<String, Object> kycStatus(@RequestParam String walletAddress) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("walletAddress", walletAddress);

        boolean hasKycNft = kycNftService.hasKycNft(walletAddress);
        result.put("hasKycNft", hasKycNft);
        return result;
    }

    @GetMapping("/mint")
    public String mint(@RequestParam String to) throws Exception {
        String txHash = kycNftService.mintKycNft(to);
        return "Token minted! Transaction Hash: " + txHash;
    }
}