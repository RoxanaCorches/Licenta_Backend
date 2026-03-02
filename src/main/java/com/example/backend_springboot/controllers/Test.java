package com.example.backend_springboot.controllers;

import com.example.backend_springboot.blockchain.BlockchainConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private final BlockchainConfig blockchainConfig;

    public Test(BlockchainConfig blockchainConfig) {
        this.blockchainConfig = blockchainConfig;
    }

    @GetMapping("/mint")
    public String mint(@RequestParam String to) throws Exception {
        String txHash = blockchainConfig.mint(to);
        return "Token mintuit! TxHash: " + txHash;
    }
}