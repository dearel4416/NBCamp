package com.sparta.newsfeedt6.user.controller;
import com.sparta.newsfeedt6.user.dto.SignupRequestDto;
import com.sparta.newsfeedt6.user.service.UserService;
import com.sparta.newsfeedt6.user.dto.LoginRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);

        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {

        userService.login(requestDto, res);

        HttpHeaders headers = new HttpHeaders();


        return ResponseEntity.ok().headers(headers).body("로그인에 성공하였습니다.");
    }

    // 이메일 인증 관련 메소드 2개
    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid String email) {
        userService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity<String> verificationEmail(@RequestParam("email") @Valid String email,
                                            @RequestParam("code") String authCode) {
        Boolean isVerified = userService.verifyCode(email, authCode);

        if (isVerified) {
            return ResponseEntity.ok("이메일 인증에 성공하였습니다");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패");
        }
    }
}
