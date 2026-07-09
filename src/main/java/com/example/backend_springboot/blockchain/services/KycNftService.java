package com.example.backend_springboot.blockchain.services;

import com.example.backend_springboot.blockchain.contracts.KYCNFT;
import org.springframework.stereotype.Service;

@Service
public class KycNftService {
     private KYCNFT kycNft;

     public KycNftService(KYCNFT kycNft) {
         this.kycNft = kycNft;
     }

    public boolean hasKycNft(String walletAddress) throws Exception {
        if(kycNft.hasMinted(walletAddress).send()) {
            return true;
        }
        else {
            return false;
        }
    }

    public String mintKycNft(String to) throws Exception {
        boolean alreadyMinted = kycNft.hasMinted(to).send();
        if (alreadyMinted) {
            throw new IllegalStateException("It has already been minted at the address:" + to);
        }
        var resp = kycNft.mint(to).send();
        return resp.getTransactionHash();
    }
}
