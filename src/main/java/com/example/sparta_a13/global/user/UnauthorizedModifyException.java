package com.example.sparta_a13.global.user;

import com.example.sparta_a13.global.common.BusinessException;
import com.example.sparta_a13.global.common.ErrorCode;

public class UnauthorizedModifyException extends BusinessException {

  public UnauthorizedModifyException() {
    super(ErrorCode.UNAUTHORIZED_MODIFY_EXCEPTION);
  }
}
