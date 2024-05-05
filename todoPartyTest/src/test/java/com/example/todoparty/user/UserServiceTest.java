package com.example.todoparty.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    User user;

    @Test
    @DisplayName("회원 가입 등록")
    void Test1() throws Exception {
        // given
        String username = "username";
        String password = "password";
        UserRequestDTO userRequestDTO = new UserRequestDTO(username, password);
        // when
        userService.signup(userRequestDTO);
        // then
        if(userRepository.findByUsername(username).isPresent()){
            System.out.println("회원 가입 성공");
        }
    }

    @Test
    @DisplayName("로그인")
    void Test2(){
        // given
        String username = "username";
        String password = "password";
        UserRequestDTO userRequestDTO = new UserRequestDTO(username, password);
        // when & then
        try {
            userService.login(userRequestDTO);
            System.out.println("로그인 성공");
        } catch (Exception e){
            System.err.println("로그인 실패");
        }
    }
}