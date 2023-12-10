package com.example.sparta_a13.global.user;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException() {
    super(ErrorCode.NOT_FOUND_USER_EXCEPTION);
  }
}
