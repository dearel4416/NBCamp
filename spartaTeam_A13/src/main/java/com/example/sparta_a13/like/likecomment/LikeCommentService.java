package com.example.sparta_a13.like.likecomment;

import com.example.sparta_a13.comment.entity.Comment;
import com.example.sparta_a13.comment.repository.CommentRepository;
import com.example.sparta_a13.global.comment.CommentNotFoundException;
import com.example.sparta_a13.global.comment.SelfLikeCommentException;
import com.example.sparta_a13.global.like.DuplicatedLikeException;
import com.example.sparta_a13.global.like.NotFoundLikeException;
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

    // 댓글이 있는지 확인
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(CommentNotFoundException::new);

    // 사용자가 댓글 작성자인지 확인
    if (comment.getUser().getUsername().equals(loginUser.getUsername())) {
      throw new SelfLikeCommentException();
    }

    // 이미 좋아요를 했는지 확인
    if (likeCommentRepository.findByUserAndComment(loginUser, comment).isPresent()) {
      throw new DuplicatedLikeException();
    }

    LikeComment likeComment = LikeComment.fromUserAndComment(loginUser, comment);
    likeCommentRepository.save(likeComment);

    comment.setLikeCount(comment.getLikeCount() + 1);
    commentRepository.save(comment);

    return likeComment;
  }

  // 댓글 좋아요 취소하기
  @Transactional
  public void unLikeComment(User loginUser, Long commentId) {

    // 댓글이 있는지 확인
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(CommentNotFoundException::new);

    // 좋아요가 있는지 확인
    LikeComment likeComment = likeCommentRepository.findByUserAndComment(loginUser, comment)
        .orElseThrow(NotFoundLikeException::new);

    likeCommentRepository.deleteById(likeComment.getId());

    comment.setLikeCount(comment.getLikeCount() - 1);
    commentRepository.save(comment);
  }
}

