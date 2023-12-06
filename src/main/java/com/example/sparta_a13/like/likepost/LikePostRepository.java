package com.example.sparta_a13.like.likepost;

import com.example.sparta_a13.post.Post;
import com.example.sparta_a13.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

  Optional<LikePost> findByUserAndPost(User user, Post post);

}
