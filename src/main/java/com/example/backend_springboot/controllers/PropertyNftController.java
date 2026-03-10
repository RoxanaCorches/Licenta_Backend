package com.example.backend_springboot.controllers;


import com.example.backend_springboot.blockchain.MintNftProperty;
import com.example.backend_springboot.blockchain.services.PropertyNFTService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propertyNft")
public class PropertyNftController {
    private PropertyNFTService propertyNFTService;

    public PropertyNftController(PropertyNFTService propertyNFTService) {
        this.propertyNFTService = propertyNFTService;
    }

    @PostMapping("/mint")
    public String mint(@RequestBody MintNftProperty tokenURI) throws Exception {
        String txHash = propertyNFTService.mint(tokenURI.getTokenUri());
        return "Token minted! Transaction Hash: " + txHash;
    }
}
