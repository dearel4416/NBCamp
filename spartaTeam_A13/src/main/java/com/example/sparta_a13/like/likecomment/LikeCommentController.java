package com.example.sparta_a13.like.likecomment;

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
@RequestMapping("/api/comment/{commentId}/like")
@RequiredArgsConstructor
public class LikeCommentController {

  private final LikeCommentService likeCommentService;

  // 댓글 좋아요 하기
  @PostMapping
  public ResponseEntity<?> likeComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    likeCommentService.likeComment(loginUser, commentId);

    return ResponseEntity.status(HttpStatus.CREATED).body("댓글 좋아요");

  }

  // 댓글 좋아요 취소하기
  @DeleteMapping
  public ResponseEntity<?> unLikeComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    likeCommentService.unLikeComment(loginUser, commentId);

    return ResponseEntity.ok().body("댓글 좋아요 취소");

  }

}
