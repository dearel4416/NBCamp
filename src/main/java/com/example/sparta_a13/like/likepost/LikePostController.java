package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<?> likePost(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    likePostService.likePost(loginUser, postId);
    return ResponseEntity.status(HttpStatus.CREATED).body("요청 성공");
  }

  // 게시글 좋아요 취소하기
  @DeleteMapping
  public ResponseEntity<?> unLikePost(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    likePostService.unLikePost(loginUser, postId);
    return ResponseEntity.ok().body("요청 성공");
  }
}
