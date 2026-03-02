package com.example.backend_springboot;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class GenerateContracts {
    public static void main(String[] args) throws Exception {
        SolidityFunctionWrapperGenerator.main(new String[]{
                "-a", "src/main/resources/contracts/KYCNFT.abi",
                "-o", "src/main/java",
                "-p", "com.example.backend_springboot.contract"
        });
    }
}