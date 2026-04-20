package com.example.backend_springboot.dtos.userDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadImageProfileDTO {
    private MultipartFile file;
    public UploadImageProfileDTO(MultipartFile file) {
        this.file = file;
    }
}
