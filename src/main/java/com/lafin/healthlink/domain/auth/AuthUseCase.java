package com.lafin.healthlink.domain.auth;

import com.lafin.healthlink.domain.user.dto.CreateUserDto;

import java.util.Optional;

public interface AuthUseCase {
  Optional<Token> login(String email, String password);
  Optional<Token> register(CreateUserDto dto);
  Optional<Token> refresh(String accessToken, String refreshToken);
}
