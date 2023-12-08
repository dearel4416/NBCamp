package com.example.sparta_a13.global.follow;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class DuplicatedFollowException extends BusinessException {

  public DuplicatedFollowException() {
    super(ErrorCode.DUPLICATED_FOLLOW_EXCEPTION);
  }
}
