package com.example.backend_springboot.blockchain.services;


import com.example.backend_springboot.blockchain.contracts.PropertyNFT;
import org.springframework.stereotype.Service;

@Service
public class PropertyNFTService {
    private PropertyNFT propertyNFT;

    public PropertyNFTService(PropertyNFT propertyNFT) {
        this.propertyNFT = propertyNFT;
    }
}
