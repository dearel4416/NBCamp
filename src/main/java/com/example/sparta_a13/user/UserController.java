package com.example.sparta_a13.user;

import com.example.sparta_a13.CommonResponseDto;
import com.example.sparta_a13.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserInfoRequestDTO infoRequestDTO){
        try {
            userService.signup(infoRequestDTO);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 username", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원 가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")

    public ResponseEntity<CommonResponseDto> login(@RequestBody UserRequestDTO userRequestDto, HttpServletResponse response){

        try {
            userService.login(userRequestDto);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        response.setHeader(JwtUtil.AUTH_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));

        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonResponseDTO> logout(HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails){
        response.setHeader(null,null);
        return ResponseEntity.ok().body(new CommonResponseDTO("로그아웃 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/profile/{userId}")
    public ResponseEntity<CommonResponseDTO> modifyUserInfo(@PathVariable Long userId, @RequestBody UserInfoModifyRequestDTO requestDTO, HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            userService.modifyUserInfo(requestDTO, userDetails.getUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        response.setHeader(null,null);
        return ResponseEntity.ok().body(new CommonResponseDTO("프로필 수정 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/profile/{userId}/password")
    public ResponseEntity<CommonResponseDTO> modifyUserPassword(@PathVariable Long userId, @RequestBody UserPWModifyRequestDTO requestDTO, HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            userService.modifyUserPassword(requestDTO, userDetails.getUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        response.setHeader(null,null);
        return ResponseEntity.ok().body(new CommonResponseDTO("비밀번호 변경 성공", HttpStatus.OK.value()));
    }
}
