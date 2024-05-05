package com.example.todoparty.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("UserRequestDTO 테스트")
class UserRequestDTOTest {
    String[] usernames = {"qwer1234", "11111111111", "A", "&", "이름"};
    String[] passwords = {"QwEr1234", "111111111111111111", "喝", "%", "비번"};
    @Test
    @DisplayName("UserRequestDTO SET & GET")
    void test(){
        // given
        UserRequestDTO requestDTO = new UserRequestDTO();
        // when & then
        for(int i = 0; i < usernames.length; i++){
            try {
                requestDTO.setUsername(usernames[i]);
                requestDTO.setPassword(passwords[i]);
                if(requestDTO.getUsername().equals(usernames[i])){
                    System.out.println("유저 이름 성공");
                } else {
                    System.err.println("유저 이름 실패");
                } if(requestDTO.getPassword().equals(passwords[i])){
                    System.out.println("비밀 번호 성공");
                } else {
                    System.err.println("비밀 번호 실패");
                }
            } catch (Exception e) {
                System.err.println("범위 오류");
            }
        }
    }
}