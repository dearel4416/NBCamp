package com.example.sparta_a13.post.response;

import com.example.sparta_a13.CommonResponseDTO;
import com.example.sparta_a13.post.Post;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class PostResponseDto extends CommonResponseDTO {
    private Long id;
    private String postTitle;
    private String username;
    private String postContent;
    private LocalDateTime createDate;
    private LocalDateTime modifiedAt;

    public PostResponseDto(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.username =post.getUsername();
        this.postContent = post.getPostContent();
        this.createDate = post.getCreateDate();
        this.modifiedAt=post.getModifiedAt();
    }
}