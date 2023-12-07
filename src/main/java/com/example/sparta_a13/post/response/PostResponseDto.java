package com.example.sparta_a13.post.response;

import com.example.sparta_a13.CommonResponseDto;
import com.example.sparta_a13.post.Post;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class PostResponseDto extends CommonResponseDto {
    private Long id;
    private String postTitle;
    private String userName;
    private String postContent;
    private Boolean isCompleted;
    //private UserDTO user;
    private LocalDateTime createDate;

    public PostResponseDto(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.userName=post.getUsername();
        this.postContent = post.getPostContent();
        this.isCompleted = post.getIsCompleted();
        //this.user = new UserDTO(post.getUser());
        this.createDate = post.getCreateDate();
    }
}