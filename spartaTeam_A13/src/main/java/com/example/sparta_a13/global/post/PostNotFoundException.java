package com.example.sparta_a13.global.post;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class PostNotFoundException extends BusinessException {

  public PostNotFoundException() {
    super(ErrorCode.NOT_FOUND_POST_EXCEPTION);
  }
}
