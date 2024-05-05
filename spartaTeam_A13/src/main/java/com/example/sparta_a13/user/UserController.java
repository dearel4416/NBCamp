package com.example.sparta_a13.user;

import com.example.sparta_a13.CommonResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signup(
        @Valid @RequestBody UserInfoRequestDTO infoRequestDTO) {
        try {
            userService.signup(infoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDTO("회원 가입 성공", HttpStatus.CREATED.value()));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO("중복된 username", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO> login(@RequestBody UserRequestDTO userRequestDto,
        HttpServletResponse response) {

        try {
            userService.login(userRequestDto, response);
            return ResponseEntity.ok().body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login/social")
    public ResponseEntity<CommonResponseDTO> socialLogin(@RequestParam String code,
        HttpServletResponse response) {
        try {
            userService.socailLogin(code, response);
            return ResponseEntity.ok()
                .body(new CommonResponseDTO("소셜 로그인 성공", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonResponseDTO> logout(HttpServletResponse response) {
        response.setHeader(null, null);
        return ResponseEntity.ok().body(new CommonResponseDTO("로그아웃 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<CommonResponseDTO> getProfile(@PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            UserResponseDTO userResponseDTO = userService.getProfile(userDetails.getUser());
            return ResponseEntity.ok().body(userResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/profile/{userId}")
    public ResponseEntity<CommonResponseDTO> modifyUserInfo(@PathVariable Long userId,
        @RequestBody UserInfoModifyRequestDTO requestDTO, HttpServletResponse response,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            userService.modifyUserInfo(requestDTO, userDetails.getUser());
            response.setHeader(null, null);
            return ResponseEntity.ok()
                .body(new CommonResponseDTO("프로필 수정 성공", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/profile/{userId}/password")
    public ResponseEntity<CommonResponseDTO> modifyUserPassword(@PathVariable Long userId,
        @RequestBody UserPWModifyRequestDTO requestDTO, HttpServletResponse response,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            userService.modifyUserPassword(requestDTO, userDetails.getUser());
            response.setHeader(null, null);
            return ResponseEntity.ok()
                .body(new CommonResponseDTO("비밀번호 변경 성공", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/ladder")
    public ResponseEntity<UserResponseDTO> getLadderUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        UserResponseDTO responseDTO = userService.getLadderUser(user);
        return ResponseEntity.ok().body(responseDTO);
    }
}
