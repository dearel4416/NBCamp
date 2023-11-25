package com.sparta.newsfeedt6.user.controller;
import com.sparta.newsfeedt6.dto.SignupRequestDto;
import com.sparta.newsfeedt6.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "redirect:/api/user/login";
    }
}
