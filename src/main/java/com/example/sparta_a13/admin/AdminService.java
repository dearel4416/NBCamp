package com.example.sparta_a13.admin;

import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserRepository;
import com.example.sparta_a13.user.UserResponseDTO;
import com.example.sparta_a13.user.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    public List<UserResponseDTO> getUserList() {
            List<User> users = userRepository.findAll();
            List<UserResponseDTO> userList = new ArrayList<>();
            users.forEach((user) -> userList.add(new UserResponseDTO(user.getUsername(), user.getEmail(), user.getIntroduce(), user.getRole() == UserRoleEnum.ROLE_ADMIN)));
            return userList;
    }
}
