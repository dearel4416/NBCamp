package com.example.sparta_a13.like.likecomment;

import com.example.sparta_a13.comment.Comment;
import com.example.sparta_a13.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {

  Optional<LikeComment> findByUserAndComment(User user, Comment comment);

}
