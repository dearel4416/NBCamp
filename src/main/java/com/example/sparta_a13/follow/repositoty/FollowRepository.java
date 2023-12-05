package com.example.sparta_a13.follow.repositoty;

import com.example.sparta_a13.follow.entity.Follow;
import com.example.sparta_a13.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
  Optional<Follow> findByFollowingAndFollower(User following, User follower);
}
