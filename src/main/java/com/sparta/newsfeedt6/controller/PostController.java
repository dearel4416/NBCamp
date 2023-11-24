package com.sparta.newsfeedt6.controller;

import com.sparta.newsfeedt6.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.dto.PostResponseDto;
import com.sparta.newsfeedt6.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    // 게시글 등록 (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto addPost(
            @RequestBody PostAddRequestDto requestDto
    ){
        PostResponseDto responseDto = postService.addPost(requestDto);
        return responseDto;
    }

    // 게시글 선택 조회 (GET) : by ID
    @GetMapping("/{postId}")
    public PostResponseDto getPost(
            @PathVariable Long postId
    ){
        return postService.getPost(postId);
    }
}