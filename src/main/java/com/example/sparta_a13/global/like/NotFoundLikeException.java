package com.example.sparta_a13.global.like;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class NotFoundLikeException extends BusinessException {

  public NotFoundLikeException() {
    super(ErrorCode.NOT_FOUND_LIKE_EXCEPTION);
  }
}
