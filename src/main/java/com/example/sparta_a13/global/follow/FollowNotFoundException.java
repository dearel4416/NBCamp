package com.example.sparta_a13.global.follow;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class FollowNotFoundException extends BusinessException {
  public FollowNotFoundException(ErrorCode errorCode) {
    super(ErrorCode.NOT_FOUND_FOLLOW_EXCEPTION);
  }
}
