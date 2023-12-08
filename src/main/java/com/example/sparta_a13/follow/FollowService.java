package com.example.sparta_a13.follow;

import com.example.sparta_a13.global.follow.DuplicatedFollowException;
import com.example.sparta_a13.global.follow.FollowNotFoundException;
import com.example.sparta_a13.global.follow.SelfFollowException;
import com.example.sparta_a13.global.user.UserNotFoundException;
import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserDTO;
import com.example.sparta_a13.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
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
    User follower = checkFollowerUser(followerId);

    // 자기 자신은 팔로우 하는 경우
    if (user.getId().equals(follower.getId())) {
      throw new SelfFollowException();
    }

    // 이미 팔로우 한 경우
    if (isAlreadyFollow(user, follower)) {
      throw new DuplicatedFollowException();
    }

    Follow follow = new Follow(user, follower);
    return followRepository.save(follow);
  }

  // 팔로우 취소하기
  public void unfollowUser(String username, Long followerId) {
    User user = checkUser(username);
    User follower = checkFollowerUser(followerId);

    // 팔로우 한 적이 없는 경우
    if (!isAlreadyFollow(user, follower)) {
      throw new FollowNotFoundException();
    }
    Optional<Follow> follow = followRepository.findByFollowingAndFollower(user, follower);

    followRepository.delete(follow.get());
  }

  // 팔로우 목록 조회하기 (로그인한 유저가 팔로우 한 유저들의 목록)
  public List<UserDTO> getFollowers(String username) {
    User user = checkUser(username);
    List<Follow> followers = followRepository.findByFollowing(user);

    List<UserDTO> followerDTOList = new ArrayList<>();
    for (Follow follow : followers) {
      User follower = follow.getFollower();
      UserDTO userDTO = new UserDTO(follower.getUsername());
      followerDTOList.add(userDTO);
    }

    return followerDTOList;
  }

  // 팔로잉 목록 조회하기 (로그인한 유저를 팔로우 한 유저들의 목록)
  public List<UserDTO> getFollowings(String username) {
    User user = checkUser(username);
    List<Follow> followings = followRepository.findByFollower(user);

    List<UserDTO> followingDTOList = new ArrayList<>();
    for (Follow follow : followings) {
      User following = follow.getFollowing();
      UserDTO userDTO = new UserDTO(following.getUsername());
      followingDTOList.add(userDTO);
    }

    return followingDTOList;
  }

  // 유저 확인
  private User checkUser(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UserNotFoundException();
    }
    return user.get();
  }

  // 팔로워 확인
  private User checkFollowerUser(Long followerId) {
    Optional<User> follower = userRepository.findById(followerId);
    if (follower.isEmpty()) {
      throw new UserNotFoundException();
    }
    return follower.get();
  }

  // 이미 팔로우가 되었는지 확인
  private boolean isAlreadyFollow(User following, User follower) {
    Optional<Follow> optionalFollow = followRepository.findByFollowingAndFollower(following,
        follower);
    return optionalFollow.isPresent();
  }

}
