package com.example.s3nrds.controller;

import com.example.s3nrds.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/s3")
public class S3TestController {

    private final S3Service s3Service;

    public S3TestController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String objectKey =
                "users/test/profile/" + file.getOriginalFilename();

        s3Service.uploadFile(objectKey, file);

        return ResponseEntity.ok(
                "File uploaded successfully. Object key: " + objectKey
        );
    }
}