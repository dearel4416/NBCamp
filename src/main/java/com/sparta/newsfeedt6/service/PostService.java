package com.sparta.newsfeedt6.service;

import com.sparta.newsfeedt6.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.dto.PostResponseDto;
import com.sparta.newsfeedt6.entity.PostEntity;
import com.sparta.newsfeedt6.repository.PostJpaReqository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaReqository postJpaReqository;

    // 게시글 등록 (POST)
    public PostResponseDto addPost(PostAddRequestDto requestDto) {
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePost = postJpaReqository.save(postEntity);

        return new PostResponseDto(savePost);
    }

    // 게시글 선택 조회 (GET) : by ID
    public PostResponseDto getPost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);

        return new PostResponseDto(postEntity);
    }

    // 게시글 Id 찾기
    private PostEntity getPostEntity(Long postId){
        return postJpaReqository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }
}
