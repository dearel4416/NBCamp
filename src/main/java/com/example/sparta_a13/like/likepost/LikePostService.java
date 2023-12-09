package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.global.like.DuplicatedLikeException;
import com.example.sparta_a13.global.like.NotFoundLikeException;
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
  public LikePost likePost(User loginUser, Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

    if (likePostRepository.findByUserAndPost(loginUser, post).isPresent()) {
      throw new DuplicatedLikeException();
    }

    LikePost likePost = LikePost.fromUserAndPost(loginUser, post);
    likePostRepository.save(likePost);

    post.setLikeCount(post.getLikeCount() + 1);
    postRepository.save(post);

    return likePost;
  }

  // 게시글 좋아요 취소하기
  @Transactional
  public void unLikePost(User loginUser, Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

    LikePost likePost = likePostRepository.findByUserAndPost(loginUser, post)
        .orElseThrow(NotFoundLikeException::new);

    likePostRepository.deleteById(likePost.getId());

    post.setLikeCount(post.getLikeCount() - 1);
    postRepository.save(post);
  }

}