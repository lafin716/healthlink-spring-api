package com.lafin.healthlink.app.auth;

import com.lafin.healthlink.domain.auth.AuthException;
import com.lafin.healthlink.domain.auth.AuthUseCase;
import com.lafin.healthlink.domain.auth.Token;
import com.lafin.healthlink.domain.auth.TokenRepository;
import com.lafin.healthlink.shared.jwt.DefaultJwtDto;
import com.lafin.healthlink.shared.jwt.JwtGenerator;
import com.lafin.healthlink.domain.user.UserRepository;
import com.lafin.healthlink.domain.user.UserUseCase;
import com.lafin.healthlink.domain.user.dto.CreateUserDto;
import com.lafin.healthlink.shared.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
  private final Logger logger = Logger.getLogger(AuthService.class.getName());
  private final TokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final JwtGenerator jwtGenerator;
  private final UserUseCase userUseCase;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public Optional<Token> login(String email, String password) {
    if (ValidateUtil.isBlank(email, password)) {
      throw AuthExceptionHolder.NOT_VALID_LOGIN_INPUT;
    }

    // 회원 조회
    var optFoundUser = userRepository.findUserByUserEmail(email);
    if (optFoundUser.isEmpty()) {
      throw AuthExceptionHolder.NOT_FOUND_USER;
    }

    // 회원이 있는 경우 로그인 실패 횟수 초과 여부 검사
    var foundUser = optFoundUser.get();
    if (foundUser.isOverFailCount()) {
      throw AuthExceptionHolder.OVER_FAIL_LIMIT;
    }

    // 비밀번호 검사
    if (!passwordEncoder.matches(password, foundUser.getUserPassword())) {
      // 비밀번호 미매칭 시 실패카운트 증가 후 저장
      foundUser.addFailCount();
      userRepository.save(foundUser);
      throw AuthExceptionHolder.NOT_MATCH_PASSWORD;
    }

    // 토큰 생성
    var newToken = jwtGenerator.generateToken(DefaultJwtDto.createJwtDto(foundUser));
    if (newToken == null) {
      throw AuthExceptionHolder.FAIL_CREATE_TOKEN;
    }

    // 이미 존재하는 토큰이 있는 경우 갱신처리
    var foundToken = tokenRepository.findTokenByUserId(newToken.getUserId())
        .map(token -> token.update(newToken))
        .map(tokenRepository::save);
    if (foundToken.isEmpty()) {
      foundToken = Optional.of(tokenRepository.save(newToken));
    }

    // 로그인 성공 시 실패정보 및 로그인정보 갱신
    foundUser.successLogin();
    userRepository.save(foundUser);

    return foundToken;
  }

  @Override
  public Optional<Token> register(CreateUserDto dto) {
    var savedUser = userUseCase.create(dto);
    if (savedUser == null) {
      throw AuthExceptionHolder.FAIL_CREATE_USER;
    }

    var newToken = userRepository.findUserByUserEmail(savedUser.getUserEmail())
        .map(DefaultJwtDto::createJwtDto)
        .map(jwtGenerator::generateToken)
        .orElse(null);
    if (newToken == null) {
      throw AuthExceptionHolder.FAIL_CREATE_TOKEN;
    }

    var savedToken = tokenRepository.save(newToken);
    return Optional.of(savedToken);
  }

  @Override
  public Optional<Token> refresh(String accessToken, String refreshToken) {
    if (ValidateUtil.isBlank(accessToken, refreshToken)) {
      throw AuthExceptionHolder.NOT_VALID_REFRESH_INPUT;
    }

    var foundToken = tokenRepository.findTokenByAccessToken(accessToken);
    if (foundToken.isEmpty()) {
      throw AuthExceptionHolder.NOT_FOUND_TOKEN;
    }

    return Optional.empty();
  }
}
