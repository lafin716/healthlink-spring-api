package com.lafin.healthlink.app.auth;

import com.lafin.healthlink.domain.auth.AuthUseCase;
import com.lafin.healthlink.domain.auth.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {
  @Autowired
  private AuthUseCase authUseCase;

  @Test
  public void 토큰생성_정상() {
    String email = "ys@g.c";
    String pw = "test";
    Optional<Token> newToken = authUseCase.createToken(email, pw);

    assertTrue(newToken.isPresent());
  }


}