package com.lafin.healthlink.domain.auth;

import java.util.Optional;

public interface AuthUseCase {
  Optional<Token> createToken(String email, String password);
  Optional<Token> refreshToken(String accessToken, String refreshToken);
}
