package com.example.backend_springboot.blockchain;


import com.example.backend_springboot.blockchain.contracts.KYCNFT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;


@Service
public class BlockchainConfig {
    private final Web3j web3j;
    private final Credentials credentials;
    private final KYCNFT kycNft;

    public BlockchainConfig(@Value("${rpcUrl}") String rpcUrl, @Value("${kycNftAddress}") String kycNftAddress, @Value("${privateKey}") String privateKey){
        this.web3j=Web3j.build(new HttpService(rpcUrl));
        this.credentials=Credentials.create(privateKey);

        this.kycNft = KYCNFT.load(
                kycNftAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );
    }

    public String mint(String to) throws Exception {
        var resp = kycNft.mint(to).send();
        return resp.getTransactionHash();
    }


}
