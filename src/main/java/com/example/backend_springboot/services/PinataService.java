package com.example.backend_springboot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import java.util.Map;

@Service
public class PinataService {

    @Value("${api_key_pinata}")
    private String apiKeyPinata;

    @Value("${api_secret_pinata}")
    private String apiSecretPinata;

    public String uploadFile(MultipartFile file, String nameImageOnPinata) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.pinata.cloud/pinning/pinFileToIPFS";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("pinata_api_key", apiKeyPinata);
        headers.set("pinata_secret_api_key", apiSecretPinata);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), nameImageOnPinata));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

        return response.getBody().get("IpfsHash").toString();
    }

    public String uploadMetadata(byte[] jsonFile, String nameFileMetadata) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.pinata.cloud/pinning/pinFileToIPFS";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("pinata_api_key", apiKeyPinata);
        headers.set("pinata_secret_api_key", apiSecretPinata);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(new ByteArrayInputStream(jsonFile), nameFileMetadata));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

        return response.getBody().get("IpfsHash").toString();
    }

    public class MultipartInputStreamFileResource extends InputStreamResource {

        private final String filename;

        public MultipartInputStreamFileResource(InputStream inputStream, String filename) {
            super(inputStream);
            this.filename = filename;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public long contentLength() throws IOException {
            return -1;
        }
    }
}