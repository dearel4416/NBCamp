package com.example.sparta_a13.global.post;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class SelfLikePostException extends BusinessException {

  public SelfLikePostException() {
    super(ErrorCode.SELF_LIKE_POST_EXCEPTION);
  }
}
