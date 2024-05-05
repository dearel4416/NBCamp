package com.example.sparta_a13.global.comment;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class SelfLikeCommentException extends BusinessException {

  public SelfLikeCommentException() {
    super(ErrorCode.SELF_LIKE_COMMENT_EXCEPTION);
  }
}
