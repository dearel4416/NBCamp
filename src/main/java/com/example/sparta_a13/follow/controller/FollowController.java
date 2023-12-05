package com.example.sparta_a13.follow.controller;

import com.example.sparta_a13.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  // 팔로우 하기
  @PostMapping("/{followerId}/follow")
  public ResponseEntity<?> followUser(@PathVariable Long followerId) {

    // 임시 유저
    String username = "test username";

    followService.followUser(username, followerId);
    return ResponseEntity.status(HttpStatus.OK).body("요청 성공");
  }

}
