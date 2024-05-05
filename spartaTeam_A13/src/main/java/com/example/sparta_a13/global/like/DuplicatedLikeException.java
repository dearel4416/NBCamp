package com.example.sparta_a13.global.like;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class DuplicatedLikeException extends BusinessException {

  public DuplicatedLikeException() {
    super(ErrorCode.DUPLICATED_LIKE_EXCEPTION);
  }
}
