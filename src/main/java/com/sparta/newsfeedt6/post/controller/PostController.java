package com.sparta.newsfeedt6.post.controller;

import com.sparta.newsfeedt6.post.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.post.dto.PostResponseDto;
import com.sparta.newsfeedt6.post.dto.PostUpdateRequestDto;
import com.sparta.newsfeedt6.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 게시글 전체 조회
    @GetMapping
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public PostResponseDto updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto requestDto
    ){
        return postService.updatePost(postId, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public void deletePost(
            @PathVariable Long postId
    ){
        postService.deletePost(postId);
    }
}