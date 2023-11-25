package com.sparta.newsfeedt6.entity;

import com.sparta.newsfeedt6.comment.entity.Comment;
import com.sparta.newsfeedt6.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends com.sparta.newsfeedt6.entity.TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 500)
    private String content;

    // 댓글과 게시글 연관관계 설정
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public PostEntity(PostAddRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    // 게시글 수정
    public void update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}