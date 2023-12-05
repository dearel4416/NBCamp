package com.example.sparta_a13.follow.service;

import com.example.sparta_a13.follow.entity.Follow;
import com.example.sparta_a13.follow.repositoty.FollowRepository;
import com.example.sparta_a13.global.follow.DuplicatedFollowException;
import com.example.sparta_a13.global.user.UserNotFoundException;
import com.example.sparta_a13.user.entity.User;
import com.example.sparta_a13.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;

  private final UserRepository userRepository;

    // 팔로우 하기
    public Follow followUser(String username, Long followerId) {
      User user = checkUser(username);
      User follower = checkFollower(followerId);

      // 이미 팔로우 한 경우
      if (isAlreadyFollow(user, follower)) {
        throw new DuplicatedFollowException();
      }

      Follow follow = new Follow(user, follower);
      return followRepository.save(follow);
    }

  // 유저 확인
  private User checkUser(String username) {
    Optional<User> user = userRepository.findByUserName(username);
    if (user.isEmpty()) {
      throw new UserNotFoundException();
    }
    return user.get();
  }

  // 팔로워 확인
  private User checkFollower(Long followerId) {
    Optional<User> follower = userRepository.findByUserId(followerId);
    if (follower.isEmpty()) {
      throw new UserNotFoundException();
    }
    return follower.get();
  }

  // 이미 팔로우가 되었는지 확인
  private boolean isAlreadyFollow(User following, User follower) {
    Optional<Follow> optionalFollow = followRepository.findByFollowingAndFollower(following, follower);
    return optionalFollow.isPresent();
  }
}
