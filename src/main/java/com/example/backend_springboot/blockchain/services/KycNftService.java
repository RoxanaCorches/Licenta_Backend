package com.example.backend_springboot.blockchain.services;

import com.example.backend_springboot.blockchain.contracts.KYCNFT;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class KycNftService {
     private KYCNFT kycNft;

     public KycNftService(KYCNFT kycNft) {
         this.kycNft = kycNft;
     }

    public String mint(String to) throws Exception {
        boolean alreadyMinted = kycNft.hasMinted(to).send();
        if (alreadyMinted) {
            throw new IllegalStateException("A fost mintat deja la adresa:" + to);
        }
        var resp = kycNft.mint(to).send();
        return resp.getTransactionHash();
    }

    @PostConstruct
    public void owner ()  throws Exception {
       System.out.println("Owner:" + kycNft.owner().send());
    }
}
