package com.lafin.healthlink.app.auth;

import com.lafin.healthlink.domain.auth.AuthException;

public class AuthExceptionHolder {
  public static final AuthException NOT_VALID_LOGIN_INPUT = new AuthException("로그인 입력 정보가 잘못되었습니다.");
  public static final AuthException NOT_FOUND_USER = new AuthException("회원정보를 찾을 수 없습니다.");
  public static final AuthException NOT_FOUND_TOKEN = new AuthException("토큰을 찾을 수 없습니다.");
  public static final AuthException OVER_FAIL_LIMIT = new AuthException("허용 로그인 시도가 초과되었습니다.");
  public static final AuthException NOT_MATCH_PASSWORD = new AuthException("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
  public static final AuthException FAIL_CREATE_TOKEN = new AuthException("토큰 생성 중 오류가 발생하였습니다. 다시 시도해주세요.");
  public static final AuthException FAIL_CREATE_USER = new AuthException("회원정보 생성 중 오류가 발생하였습니다. 다시 시도해주세요.");
  public static final AuthException NOT_VALID_REFRESH_INPUT = new AuthException("토큰 갱신 입력 정보가 잘못되었습니다.");
}
