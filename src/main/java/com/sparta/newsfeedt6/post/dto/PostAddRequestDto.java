package com.sparta.newsfeedt6.post.dto;

import lombok.Getter;

// 게시글 등록 RequestDto
@Getter
public class PostAddRequestDto {
    private String title;
    private String content;
}