package com.sparta.newsfeedt6.dto;

import com.sparta.newsfeedt6.entity.PostEntity;
import java.time.LocalDateTime;
// 게시글 ResponseDto
public record PostResponseDto(
        Long id, // 게시글 id
        String title,
        String content,
        LocalDateTime createdAt // 게시글 저장 시점

) {
    public PostResponseDto(PostEntity savePost) {
        this(
                savePost.getId(),
                savePost.getTitle(),
                savePost.getContent(),
                savePost.getCreatedAt()
        );
    }
}
