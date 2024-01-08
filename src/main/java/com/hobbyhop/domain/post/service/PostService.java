package com.hobbyhop.domain.post.service;

import com.hobbyhop.domain.post.dto.PostRequestDTO;
import com.hobbyhop.domain.post.dto.PostResponseDTO;
import com.hobbyhop.domain.post.entity.Post;
import com.hobbyhop.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface PostService {

    Post findById(Long postId);

    PostResponseDTO createPost(Long clubId, PostRequestDTO postRequestDTO);

    PostResponseDTO getPostById(Long clubId, Long postId);


}
