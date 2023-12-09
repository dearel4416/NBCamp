package com.example.sparta_a13.user;

import com.example.sparta_a13.CommonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDTO extends CommonResponseDTO {
    private String username;
    private String email;
    private String introduce;
}
