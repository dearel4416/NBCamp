package com.sparta.newsfeedt6.controller;

import com.sparta.newsfeedt6.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.dto.PostResponseDto;
import com.sparta.newsfeedt6.dto.PostUpdateRequestDto;
import com.sparta.newsfeedt6.security.UserDetailsImpl;
import com.sparta.newsfeedt6.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @RequestBody PostAddRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        PostResponseDto responseDto = postService.addPost(requestDto, userDetails.getUser());
        return responseDto;
    }

//    public ResponseEntity<PostResponseDto> addPost(
//            @RequestBody PostAddRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ){
//        PostResponseDto responseDto = postService.addPost(requestDto, userDetails.getUser());
//        return ResponseEntity.status(201).body(responseDto);
//    }

    // 게시글 선택 조회 (GET) : by ID
    @GetMapping("/{postId}")
    public PostResponseDto getPost(
            @PathVariable Long postId
    ){
        return postService.getPost(postId);
    }

//    public ResponseEntity<PostResponseDto> getPost(
//            @PathVariable Long postId
//    ){
//        PostResponseDto responseDto = postService.getPost(postId);
//        return ResponseEntity.ok().body(responseDto);
//    }

    // 게시글 전체 조회
    @GetMapping
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public PostResponseDto updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return postService.updatePost(postId, requestDto, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public void deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        postService.deletePost(postId, userDetails.getUser());
    }
}