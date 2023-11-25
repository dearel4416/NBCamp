package com.sparta.newsfeedt6.post.service;

import com.sparta.newsfeedt6.post.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.post.dto.PostResponseDto;
import com.sparta.newsfeedt6.post.dto.PostUpdateRequestDto;
import com.sparta.newsfeedt6.post.entity.PostEntity;
import com.sparta.newsfeedt6.post.repository.PostJpaReqository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 게시글 전체 조회
    public List<PostResponseDto> getPosts(){
        return postJpaReqository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        PostEntity postEntity = getPostEntity(postId);

//        if(postEntity.getPassword().equals(requestDto.getPassword()){
//            throw new NullPointerException("작성자의 게시글만 수정할 수 있습니다.");
//        }
        postEntity.update(requestDto);

        return new PostResponseDto(postEntity);
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);

//        if(postEntity.getPassword().equals(password){
//            throw new NullPointerException("작성자의 게시글만 삭제할 수 있습니다.");
//        }
        postJpaReqository.delete(postEntity);
    }

    // 게시글 Id 찾기
    private PostEntity getPostEntity(Long postId){
        return postJpaReqository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }
}

혹시 제가 나중에 까먹을까봐 에러 나도록 적어둘게요
      1.  C U D  권한검사 로직 추가하기