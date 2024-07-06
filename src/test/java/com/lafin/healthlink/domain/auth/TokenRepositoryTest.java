package com.lafin.healthlink.domain.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TokenRepositoryTest {
  @Autowired
  private TokenRepository tokenRepository;

  @Test
  public void tokenGetTest() {
    UUID userId = UUID.fromString("0466b0b9-9280-4b7e-a6fd-f580905cd262");
    var token = tokenRepository.findTokenByUserId(userId);
    System.out.printf("token: %s", token);

  }
}