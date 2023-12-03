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
        String username = "요정";
        String password = "1q2w3e4r";
        // when
        User user = new User("요정", "1q2w3e4r");
        // then
        assertEquals(user.getUsername(), username);
        assertEquals(user.getPassword(), password);
    }
}