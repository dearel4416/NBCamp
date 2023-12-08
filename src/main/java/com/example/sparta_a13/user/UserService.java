package com.example.sparta_a13.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserInfoRequestDTO infoRequestDTO) {
        String username = infoRequestDTO.getUsername();
        String password = passwordEncoder.encode(infoRequestDTO.getPassword());
        String email = infoRequestDTO.getEmail();
        String introduce = infoRequestDTO.getIntroduce();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다");
        }

        User user = new User(username, password, email, introduce);
        userRepository.save(user);
    }

    public void login(UserRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
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

        if (!passwordEncoder.matches(beforePassword, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        } else {
            afterPassword = passwordEncoder.encode(afterPassword);
            user.setPassword(afterPassword);
        }

        userRepository.save(user);
    }
}
