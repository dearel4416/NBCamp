package com.example.sparta_a13.follow;

import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserDTO;
import com.example.sparta_a13.user.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  // 팔로우 하기
  @PostMapping("/follow/{followerId}")
  public ResponseEntity<?> followUser(@PathVariable Long followerId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    String username = userDetails.getUsername();

    followService.followUser(username, followerId);
    return ResponseEntity.status(HttpStatus.OK).body("팔로우했습니다.");
  }

  // 팔로우 취소하기
  @DeleteMapping("/follow/{followerId}")
  public ResponseEntity<?> unfollowUser(@PathVariable Long followerId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    String username = userDetails.getUsername();

    followService.unfollowUser(username, followerId);
    return ResponseEntity.status(HttpStatus.OK).body("팔로우를 취소했습니다.");
  }

  // 팔로우 목록 조회하기 (로그인한 유저가 팔로우 한 유저들의 목록)
  @GetMapping("/followers")
  public ResponseEntity<List<UserDTO>> getFollowers(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    String username = userDetails.getUsername();
    List<UserDTO> followers = followService.getFollowers(username);
    return ResponseEntity.ok(followers);
  }

  // 팔로잉 목록 조회하기 (로그인한 유저를 팔로우 한 유저들의 목록)
  @GetMapping("/followings")
  public ResponseEntity<List<UserDTO>> getFollowings(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    String username = userDetails.getUsername();
    List<UserDTO> followers = followService.getFollowings(username);
    return ResponseEntity.ok(followers);
  }
}
