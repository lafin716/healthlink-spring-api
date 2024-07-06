package com.lafin.healthlink.app.user;

import com.lafin.healthlink.domain.user.UserException;

public class UserExceptionHolder {
  public static final UserException NOT_FOUND_USER = new UserException("회원정보를 찾을 수 없습니다.");
  public static final UserException NOT_VALID_USER_INPUT = new UserException("회원정보 입력이 잘못되었습니다.");
  public static final UserException FAIL_CREATE_USER = new UserException("회원정보 생성 중 오류가 발생하였습니다. 다시 시도해주세요.");
  public static final UserException DUPLICATED_USER = new UserException("이미 존재하는 회원정보입니다.");
}
