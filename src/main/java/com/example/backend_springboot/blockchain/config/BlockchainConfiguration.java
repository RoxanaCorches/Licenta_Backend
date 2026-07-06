package com.example.backend_springboot.blockchain.config;

import com.example.backend_springboot.blockchain.contracts.KYCNFT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class BlockchainConfiguration {
    @Value("${rpcUrl}")
    private String rpcUrl;

    @Value("${privateKey}")
    private String privateKey;

    @Value("${kycNftAddress}")
    private String kycNftAddress;

    private KYCNFT kycNft;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey);
    }

    @Bean
    public KYCNFT kycNft(Web3j web3j, Credentials credentials) {
        return KYCNFT.load(
                kycNftAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );
    }
}


