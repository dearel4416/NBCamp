package com.example.sparta_a13.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDTO {
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    public UserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
