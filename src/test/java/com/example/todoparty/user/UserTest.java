package com.example.todoparty.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("UserEntity 테스트")
class UserTest {
    @Test
    @DisplayName("Getter 테스트")
    void test(){
        // given
        String username = "이름";
        String password = "1q2w3e4r";
        // when
        User user = new User("이름", "1q2w3e4r");
        // then
        if(user.getUsername().equals(username)){
            System.out.println("유저 이름 정상");
        } else {
            System.err.println("유저 이름 오류");
        }
        if (user.getPassword().equals(password)){
            System.out.println("비밀 번호 정상");
        } else {
            System.err.println("비밀 번호 오류");
        }
    }
}