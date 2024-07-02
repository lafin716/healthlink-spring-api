package com.lafin.healthlink.app.auth;

import com.lafin.healthlink.domain.auth.AuthUseCase;
import com.lafin.healthlink.domain.auth.Token;
import com.lafin.healthlink.domain.auth.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
  private final TokenRepository tokenRepository;

  @Override
  public Optional<Token> createToken(String email, String password) {
    if (!isOkCreateTokenParam(email, password)) {
      return Optional.empty();
    }

    return Optional.empty();
  }

  @Override
  public Optional<Token> refreshToken(String accessToken, String refreshToken) {
    return Optional.empty();
  }

  private boolean isOkCreateTokenParam(String email, String password) {
    if (Objects.isNull(email) || Objects.isNull(password)) {
      return false;
    }

    if (email.isBlank() || password.isBlank()) {
      return false;
    }

    return true;
  }
}
