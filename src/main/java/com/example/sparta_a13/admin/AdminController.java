package com.example.sparta_a13.admin;

import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserDetailsImpl;
import com.example.sparta_a13.user.UserResponseDTO;
import com.example.sparta_a13.user.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public void checkRole(User user) throws AuthenticationException {
        if(user.getRole() == UserRoleEnum.ROLE_USER){
            throw new AuthenticationException("관리자가 아닙니다.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userlist")
    public ResponseEntity<List<UserResponseDTO>> getUserList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws AuthenticationException {
        checkRole(userDetails.getUser());
        List<UserResponseDTO> userList = adminService.getUserList();
        return ResponseEntity.ok().body(userList);
    }
}
