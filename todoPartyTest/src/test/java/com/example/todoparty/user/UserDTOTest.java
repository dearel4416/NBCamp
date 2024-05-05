package com.example.todoparty.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("UserDTO 테스트")
class UserDTOTest {
    @Test
    @DisplayName("UserDTO 생성 & GET")
    void test(){
        // given & when
        UserDTO dto = new UserDTO(new User("이름", "1q2w3e4r"));
        
        // then
        if(dto.getUsername().equals("요정")){
            System.out.println("성공");
        } else{
            System.out.println("실패");
        }
    }
}