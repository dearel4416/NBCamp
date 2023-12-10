package com.example.sparta_a13.global.comment;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class CommentNotFoundException extends BusinessException {

  public CommentNotFoundException() {
    super(ErrorCode.NOT_FOUND_COMMENT_EXCEPTION);
  }
}
