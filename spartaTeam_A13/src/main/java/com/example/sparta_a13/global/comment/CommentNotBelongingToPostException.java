package com.example.sparta_a13.global.comment;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class CommentNotBelongingToPostException extends BusinessException {

  public CommentNotBelongingToPostException() {
    super(ErrorCode.COMMENT_NOT_BELONGING_TO_POST_EXCEPTION);
  }
}
