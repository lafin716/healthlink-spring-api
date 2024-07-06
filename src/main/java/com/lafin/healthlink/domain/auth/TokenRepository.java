package com.lafin.healthlink.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Long> {
  Optional<Token> findTokenByUserId(UUID userId);
  Optional<Token> findTokenByAccessToken(String accessToken);
}
