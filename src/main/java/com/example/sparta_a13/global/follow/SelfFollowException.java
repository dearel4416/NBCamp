package com.example.sparta_a13.global.follow;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class SelfFollowException extends BusinessException {

  public SelfFollowException() {
    super(ErrorCode.SELF_FOLLOW_EXCEPTION);
  }
}
