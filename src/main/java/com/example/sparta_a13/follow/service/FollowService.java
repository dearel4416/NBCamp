package com.example.sparta_a13.follow.service;

import com.example.sparta_a13.follow.repositoty.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

  private FollowRepository followRepository;

}
