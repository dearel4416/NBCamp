package com.example.sparta_a13.comment.entity;

import com.example.sparta_a13.Timestamped.Timestamped;
import com.example.sparta_a13.comment.dto.CommentRequestDto;
import com.example.sparta_a13.post.Post;
import com.example.sparta_a13.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String username;

    @Column
    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    public Comment(CommentRequestDto requestDto,User user, Post post) {
        this.content = requestDto.getContent();
        this.user = user;
        this.post = post;
        this.username = user.getUsername();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}