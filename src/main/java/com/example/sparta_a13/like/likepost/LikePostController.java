package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post/{postId}/like")
@RequiredArgsConstructor
public class LikePostController {

  private final LikePostService likePostService;

  // 게시글 좋아요 하기
  @PostMapping
  public ResponseEntity<?> likePost(@PathVariable Long postId) {

    // 임시 유저
    User user = new User("test1", "1234");

    likePostService.createLikePost(user, postId);
    return ResponseEntity.status(HttpStatus.CREATED).body("요청 성공");
  }

  // 게시글 좋아요 취소하기


}
