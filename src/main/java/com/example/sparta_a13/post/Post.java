package com.example.sparta_a13.post;

import com.example.sparta_a13.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String postTitle;

    @Column
    private String userName;

    @Column
    private String postContent;

    @Column
    private LocalDateTime createDate;

    @Column
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder

    public Post(String postTitle,String userName, String postContent) {
        this.postTitle = postTitle;
        this.userName= userName;
        this.postContent = postContent;
    }

    public Post(PostRequestDto dto) {
        this.postTitle = dto.getPostTitle();
        this.userName= dto.getUserName();
        this.postContent = dto.getPostContent();
        this.createDate = LocalDateTime.now();
        this.isCompleted = false;
    }

    // 연관관계 메서드
    public void setUser(User user) {
        this.user = user;
    }

    // 서비스 메서드
    public void setTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setContent(String postContent) {
        this.postContent = postContent;
    }

    public void complete() {
        this.isCompleted = true;
    }
}