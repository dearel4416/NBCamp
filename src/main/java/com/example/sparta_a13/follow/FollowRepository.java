package com.example.sparta_a13.follow;

import com.example.sparta_a13.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

  Optional<Follow> findByFollowingAndFollower(User following, User follower);

  List<Follow> findByFollowing(User user);

  List<Follow> findByFollower(User user);

}
