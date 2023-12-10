package com.example.sparta_a13.user;


import com.example.sparta_a13.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // adminToken
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(UserInfoRequestDTO infoRequestDTO) {
        String username = infoRequestDTO.getUsername();
        String password = passwordEncoder.encode(infoRequestDTO.getPassword());
        String email = infoRequestDTO.getEmail();
        String introduce = infoRequestDTO.getIntroduce();
        UserRoleEnum role = UserRoleEnum.ROLE_USER;

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다");
        }

        if (infoRequestDTO.isAdmin()) {
            if (!ADMIN_TOKEN.equals(infoRequestDTO.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ROLE_ADMIN;
        }

        User user = new User(username, password, email, introduce, role);
        //userRepository.resetId();
        userRepository.save(user);
    }


    public void login(UserRequestDTO requestDTO, HttpServletResponse response) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        response.setHeader(JwtUtil.AUTH_HEADER, jwtUtil.createToken(requestDTO.getUsername(), user.getRole()));
    }

    public void socailLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드 Service 전달 후 인증 처리 및 JWT 반환
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getToken(code);
        // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        KakaoUserInfoDTO kakaoUserInfo = getKakaoUserInfo(accessToken);
        // 3. 필요시에 회원가입
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);
        // 4. JWT 토큰 반환
        String token = jwtUtil.createToken(kakaoUser.getUsername(), kakaoUser.getRole());
        // Cookie 생성 및 직접 브라우저에 Set
        Cookie cookie = new Cookie(JwtUtil.AUTH_HEADER, token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public UserResponseDTO getProfile(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String introduce = user.getIntroduce();
        boolean isAdmin = (user.getRole() == UserRoleEnum.ROLE_ADMIN);

        return new UserResponseDTO(username, email, introduce, isAdmin);
    }

    public void modifyUserInfo(UserInfoModifyRequestDTO requestDTO, User user) {
        String username = requestDTO.getUsername();
        String email = requestDTO.getEmail();
        String introduce = requestDTO.getIntroduce();
        if (!username.isEmpty()) {
            if (userRepository.findByUsername(username).isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 유저 입니다");
            } else if (email.isEmpty() || introduce.isEmpty()) {
                if (email.isEmpty()) {
                    email = user.getEmail();
                }
                if (introduce.isEmpty()) {
                    introduce = user.getIntroduce();
                }
            }
        } else {
            username = user.getUsername();
            if (email.isEmpty()) {
                email = user.getEmail();
            }
            if (introduce.isEmpty()) {
                introduce = user.getIntroduce();
            }
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setIntroduce(introduce);

        userRepository.save(user);
    }

    public void modifyUserPassword(UserPWModifyRequestDTO requestDTO, User user) {
        String beforePassword = requestDTO.getBeforePassword();
        String afterPassword = requestDTO.getAfterPassword();
        String[] past;

        try {
            past = user.getPastPassword().split(" ");
        } catch (NullPointerException e) {
            past = new String[0];
        }

        if (!passwordEncoder.matches(beforePassword, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        } else {
            for(int i = 0; i < past.length; i++){
                if(passwordEncoder.matches(afterPassword, past[i])){
                    throw new IllegalArgumentException("최근 3번안에 사용한 비밀번호는 사용할 수 없습니다.");
                }
            }
            afterPassword = passwordEncoder.encode(afterPassword);
            if(past.length == 0){
                user.setPastPassword(user.getPassword());
            } else if(past.length > 2){
                String str = user.getPastPassword().substring(past[0].length() + 1);
                str += " " + user.getPassword();
                user.setPastPassword(str);
            } else {
                user.setPastPassword(user.getPastPassword() + " " + user.getPassword());
            }
            user.setPassword(afterPassword);
        }
        userRepository.save(user);
    }

    private String getToken(String code) throws JsonProcessingException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .encode()
                .build()
                .toUri();
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "9b338056891428cf462ff87d74093580");
        body.add("redirect_uri", "http://localhost:8080/api/users/login/social");
        body.add("code", code);
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(body);
        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );
        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDTO getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .encode()
                .build()
                .toUri();
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(new LinkedMultiValueMap<>());
        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();
        log.info("카카오 사용자 정보: " + id + ", " + nickname + ", " + email);
        return  new KakaoUserInfoDTO(id, nickname, email);
    }

    private User registerKakaoUserIfNeeded(KakaoUserInfoDTO kakaoUserInfo) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfo.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);
        if (kakaoUser == null) {
            // 카카오 사용자 email 동일한 email 가진 회원이 있는지 확인
            String kakaoEmail = kakaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kakaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // 기존 회원정보에 카카오 Id 추가
                kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
            } else {
                // 신규 회원가입
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);
                // email: kakao email
                String email = kakaoUserInfo.getEmail();
                String introduce = " ";
                kakaoUser = new User(kakaoUserInfo.getNickname(), encodedPassword, email, introduce, UserRoleEnum.ROLE_USER, kakaoId);
            }
            //userRepository.resetId();
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }
}
