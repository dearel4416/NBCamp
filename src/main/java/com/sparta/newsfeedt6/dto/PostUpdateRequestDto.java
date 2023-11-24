package com.sparta.newsfeedt6.dto;

import lombok.Getter;

// 게시글 수정 RequestDto
@Getter
public class PostUpdateRequestDto {
    private String title;
    private String content;
}
