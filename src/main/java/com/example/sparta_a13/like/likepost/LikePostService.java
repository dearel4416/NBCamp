package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.global.like.DuplicatedLikeException;
import com.example.sparta_a13.global.post.PostNotFoundException;
import com.example.sparta_a13.post.Post;
import com.example.sparta_a13.post.PostRepository;
import com.example.sparta_a13.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikePostService {

  private final LikePostRepository likePostRepository;

  private final PostRepository postRepository;

  // 게시글 좋아요 하기
  @Transactional
  public LikePost createLikePost(User user, Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

    if (likePostRepository.findByUserAndPost(user, post).isPresent()) {
      throw new DuplicatedLikeException();
    }

    LikePost likePost = LikePost.fromUserAndPost(user, post);
    return likePostRepository.save(likePost);
  }

  // 게시글 좋아요 취소하기

}
