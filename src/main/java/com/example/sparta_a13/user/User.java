package com.example.sparta_a13.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column
    private String pastPassword;

    private Long kakaoId;

    public User(String username, String password, String email, String introduce, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.role = role;
    }

    public User(String username, String password, String email, String introduce, UserRoleEnum role, Long kakaoId){
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.role = role;
        this.kakaoId =kakaoId;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}
