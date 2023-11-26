package com.sparta.newsfeedt6.post.service;

/*<<<<<<< HEAD:src/main/java/com/sparta/newsfeedt6/service/PostService.java
import com.sparta.newsfeedt6.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.dto.PostResponseDto;
import com.sparta.newsfeedt6.dto.PostUpdateRequestDto;
import com.sparta.newsfeedt6.entity.PostEntity;
import com.sparta.newsfeedt6.entity.User;
import com.sparta.newsfeedt6.repository.PostJpaReqository;
=======
>>>>>>> d74a1bb47ce36b5837242fe9f943ad161f255799:src/main/java/com/sparta/newsfeedt6/post/service/PostService.java*/
import com.sparta.newsfeedt6.post.dto.PostAddRequestDto;
import com.sparta.newsfeedt6.post.dto.PostResponseDto;
import com.sparta.newsfeedt6.post.dto.PostUpdateRequestDto;
import com.sparta.newsfeedt6.post.entity.PostEntity;
import com.sparta.newsfeedt6.post.repository.PostJpaReqository;
import com.sparta.newsfeedt6.user.entity.User;
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
    public PostResponseDto addPost(PostAddRequestDto requestDto, User user) {
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
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto, User user) {
        PostEntity postEntity = getPostEntity(postId);

        postEntity.update(requestDto);

        return new PostResponseDto(postEntity);
    }

    // 게시글 삭제
    public void deletePost(Long postId, User user) {
        PostEntity postEntity = getPostEntity(postId);

        postJpaReqository.delete(postEntity);
    }

    // 게시글 Id 찾기
    private PostEntity getPostEntity(Long postId){
        return postJpaReqository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }
}

/*혹시 제가 나중에 까먹을까봐 에러 나도록 적어둘게요
      1.  C U D  권한검사 로직 추가하기
*/