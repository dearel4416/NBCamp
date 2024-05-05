package com.example.todoparty.user;

import com.example.todoparty.configuration.WebSecurityConfig;
import com.example.todoparty.jwt.JwtUtil;
import com.example.todoparty.mvc.MockSpringSecurityFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {UserController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)

class UserControllerTest {
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    JwtUtil jwtUtil;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    @Test
    @DisplayName("회원 가입 요청")
    void test1() throws Exception {
        // given
        HashMap<String, String> signupRequestForm = new HashMap<>();
        signupRequestForm.put("username", "user");
        signupRequestForm.put("password", "1q2w3e4r");
        String body = objectMapper.writeValueAsString(signupRequestForm);
        System.out.println(body);
        // when - then
        mvc.perform(post("/api/user/signup")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인")
    void test2() throws Exception {
        // given
        HashMap<String, String> loginRequestForm = new HashMap<>();
        loginRequestForm.put("username", "user");
        loginRequestForm.put("password", "1q2w3e4r");
        String body = objectMapper.writeValueAsString(loginRequestForm);
        System.out.println(body);
        // when - then
        mvc.perform(post("/api/user/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}