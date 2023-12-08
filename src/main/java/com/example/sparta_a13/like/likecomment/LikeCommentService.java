package com.example.sparta_a13.like.likecomment;

import com.example.sparta_a13.comment.Comment;
import com.example.sparta_a13.comment.CommentRepository;
import com.example.sparta_a13.global.comment.CommentNotFoundException;
import com.example.sparta_a13.global.like.DuplicatedLikeException;
import com.example.sparta_a13.global.like.NotFoundLikeException;
import com.example.sparta_a13.global.post.PostNotFoundException;
import com.example.sparta_a13.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeCommentService {

  private final LikeCommentRepository likeCommentRepository;

  private final CommentRepository commentRepository;

  // 댓글 좋아요 하기
  @Transactional
  public LikeComment likeComment(User loginUser, Long commentId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(CommentNotFoundException::new);

    if (likeCommentRepository.findByUserAndComment(loginUser, comment).isPresent()) {
      throw new DuplicatedLikeException();
    }

    LikeComment likeComment = LikeComment.fromUserAndComment(loginUser, comment);
    return likeCommentRepository.save(likeComment);
  }

  // 댓글 좋아요 취소하기
  @Transactional
  public void unLikeComment(User loginUser, Long commentId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(CommentNotFoundException::new);

    LikeComment likeComment = likeCommentRepository.findByUserAndComment(loginUser, comment)
        .orElseThrow(NotFoundLikeException::new);

    likeCommentRepository.deleteById(likeComment.getId());
  }
}

