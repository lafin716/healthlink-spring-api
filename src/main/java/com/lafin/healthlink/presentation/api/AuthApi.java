package com.lafin.healthlink.presentation.api;

import com.lafin.healthlink.domain.auth.AuthUseCase;
import com.lafin.healthlink.domain.auth.Token;
import com.lafin.healthlink.domain.user.dto.CreateUserDto;
import com.lafin.healthlink.presentation.api.base.BaseApi;
import com.lafin.healthlink.presentation.api.base.BaseResponse;
import com.lafin.healthlink.presentation.api.req.AuthLoginReq;
import com.lafin.healthlink.presentation.api.req.AuthRefreshReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi extends BaseApi {
  private final AuthUseCase authUseCase;

  @PostMapping("/login")
  public ResponseEntity<BaseResponse<Token>> login(
      @RequestBody(required = false) AuthLoginReq req
  ) {
    if (Objects.isNull(req)) {
      return BaseResponse.fail("이메일 혹은 비밀번호를 입력해주세요").response();
    }

    var token = authUseCase.login(req.getEmail(), req.getPassword());
    return BaseResponse.ok(token).response();
  }

  @PostMapping("/register")
  public ResponseEntity<BaseResponse<Token>> register(
      @RequestBody(required = false) CreateUserDto req
  ) {
    if (Objects.isNull(req)) {
      return BaseResponse.fail("회원가입 정보를 입력해주세요.").response();
    }

    var token = authUseCase.register(req);
    return BaseResponse.ok(token).response();
  }

  @PostMapping("/refresh")
  public ResponseEntity<BaseResponse<Token>> refresh(
      @RequestBody(required = false) AuthRefreshReq req
      ) {
    if (Objects.isNull(req)) {
      return BaseResponse.fail("토큰 정보를 입력해주세요.").response();
    }

    var token = authUseCase.refresh(req.getAccessToken(), req.getRefreshToken());
    return BaseResponse.ok(token).response();
  }
}
