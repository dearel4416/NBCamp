package com.sparta.newsfeedt6.user.controller;
import com.sparta.newsfeedt6.user.dto.SignupRequestDto;
import com.sparta.newsfeedt6.user.service.UserService;
import com.sparta.newsfeedt6.user.dto.LoginRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "home";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        userService.login(requestDto, res);

        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body("로그인에 성공하였습니다.");
    }
}
