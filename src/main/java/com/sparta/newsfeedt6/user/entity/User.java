package com.sparta.newsfeedt6.user.entity;

import com.sparta.newsfeedt6.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String introduction;

    // 댓글 유저 연관관계 설정
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(String username, String password, String email, String introduction) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduction = introduction;
    }
}