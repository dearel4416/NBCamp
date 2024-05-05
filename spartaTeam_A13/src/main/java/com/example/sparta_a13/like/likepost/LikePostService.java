package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.global.like.DuplicatedLikeException;
import com.example.sparta_a13.global.like.NotFoundLikeException;
import com.example.sparta_a13.global.post.PostNotFoundException;
import com.example.sparta_a13.global.post.SelfLikePostException;
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

    // 게시글이 있는지 확인
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

    // 사용자가 글 작성자인지 확인
    if (post.getUsername().equals(loginUser.getUsername())) {
      throw new SelfLikePostException();
    }

    // 이미 좋아요를 했는지 확인
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

    // 게시글이 있는지 확인
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

    // 좋아요가 있는지 확인
    LikePost likePost = likePostRepository.findByUserAndPost(loginUser, post)
        .orElseThrow(NotFoundLikeException::new);

    likePostRepository.deleteById(likePost.getId());

    post.setLikeCount(post.getLikeCount() - 1);
    postRepository.save(post);
  }

}