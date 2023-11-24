package com.sparta.newsfeedt6.controller;

import com.sparta.newsfeedt6.dto.LoginRequestDto;
import com.sparta.newsfeedt6.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        userService.login(requestDto, res);

        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body("로그인에 성공하였습니다.");
    }
}
