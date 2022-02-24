package com.example.slack.controller;


import com.example.slack.dto.SignupRequestDto;
import com.example.slack.dto.UserInfoDto;
import com.example.slack.dto.UserResponseDto;
import com.example.slack.dto.WorkInfoDto;
import com.example.slack.model.User;
import com.example.slack.repository.UserRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.S3Uploader;
import com.example.slack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final S3Uploader s3Uploader;
    private final UserRepository userRepository;

    public UserController(UserService userService, S3Uploader s3Uploader, UserRepository userRepository) {
        this.userService = userService;
        this.s3Uploader = s3Uploader;
        this.userRepository = userRepository;
    }


    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }


    @GetMapping("/user/login/error")
    public String loginError() {
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequestDto requestDto ) {
        System.out.println(requestDto);
        userService.registerUser(requestDto);
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }


    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        String nickname = userDetails.getUser().getNickname();
//        String imageUrl = userDetails.getUser().getImageUrl();
        return new UserInfoDto(username, nickname);
    }

    @PostMapping("/user/workinfo")
    @ResponseBody
    public List<WorkInfoDto> getWorkInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return userService.getWorkInfo(username);
    }

    //user프로필 저장
    @PutMapping("/user/userprofile")
    public ResponseEntity<UserResponseDto> updateUserImage(@RequestParam("images") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            System.out.println(multipartFile);
            String imageUrl = s3Uploader.uploadFiles(multipartFile, "static");
            User user1 = userDetails.getUser();
            user1.setImageUrl(imageUrl);
            userRepository.save(user1);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

