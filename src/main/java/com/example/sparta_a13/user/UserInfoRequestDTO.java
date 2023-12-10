package com.example.sparta_a13.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequestDTO extends UserRequestDTO{
    private String email;
    private String introduce;
    private boolean admin = false;
    private String adminToken = "";

    public UserInfoRequestDTO(String username, String password) {
        super(username, password);
    }
}
