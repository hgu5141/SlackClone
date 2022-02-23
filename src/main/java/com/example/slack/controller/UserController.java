package com.example.slack.controller;


import com.example.slack.dto.SignupRequestDto;
import com.example.slack.dto.UserInfoDto;
import com.example.slack.dto.WorkInfoDto;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.S3Uploader;
import com.example.slack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final S3Uploader s3Uploader;

    public UserController(UserService userService, S3Uploader s3Uploader) {
        this.userService = userService;
        this.s3Uploader = s3Uploader;
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
        return new UserInfoDto(username, nickname);
    }

    @PostMapping("/user/workinfo")
    @ResponseBody
    public List<WorkInfoDto> getWorkInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return userService.getWorkInfo(username);
    }


}

