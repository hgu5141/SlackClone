package com.example.slack.controller;


import com.example.slack.dto.UserResponseDto;
import com.example.slack.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Uploader s3Uploader;

    @PostMapping("/api/image")
    public ResponseEntity<UserResponseDto> updateUserImage(@RequestParam("images") MultipartFile multipartFile) {
        try {
            System.out.println(multipartFile);
            s3Uploader.uploadFiles(multipartFile, "static");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
