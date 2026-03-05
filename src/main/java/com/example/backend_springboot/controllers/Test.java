package com.example.backend_springboot.controllers;

import com.example.backend_springboot.blockchain.services.KycNftService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private final KycNftService kycNftService;

    public Test(KycNftService kycNftService) {
        this.kycNftService = kycNftService;
    }


    /*
    @GetMapping("/mint")
    public ResponseEntity<String> mint(@RequestParam String to) {
        try {
            String txHash = blockchainConfig.mint(to);
            return ResponseEntity.ok("Token mintuit! TxHash: " + txHash);
        } catch (Exception e) {
            String msg = e.getMessage() == null ? "" : e.getMessage();
            if (msg.contains("Already minted")) {
                return ResponseEntity.badRequest().body("Adresa a primit deja NFT (Already minted).");
            }
            return ResponseEntity.status(500).body("Eroare la mint: " + msg);
        }
    }
     */



    @GetMapping("/mint")
    public String mint(@RequestParam String to) throws Exception {
        String txHash = kycNftService.mint(to);
        return "Token mintuit! TxHash: " + txHash;
    }
}