package com.example.sparta_a13.follow.controller;

import com.example.sparta_a13.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class FollowController {

  private FollowService followService;

  // 팔로우 하기

  // 팔로우 취소

}
