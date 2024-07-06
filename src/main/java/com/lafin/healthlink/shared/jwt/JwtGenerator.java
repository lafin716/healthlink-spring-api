package com.lafin.healthlink.shared.jwt;

import com.lafin.healthlink.domain.auth.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class JwtGenerator {
  private final String issuer;
  private final String jwtSecret;
  private final long accessTokenExpires;
  private final long refreshTokenExpires;

  public JwtGenerator(
      @Value("${jwt.issuer}") String issuer,
      @Value("${jwt.secret}") String jwtSecret,
      @Value("${jwt.access-token-expires}") long accessTokenExpires,
      @Value("${jwt.refresh-token-expires}") long refreshTokenExpires
  ) {
    this.issuer = issuer;
    this.jwtSecret = jwtSecret;
    this.accessTokenExpires = accessTokenExpires;
    this.refreshTokenExpires = refreshTokenExpires;
  }

  public Token generateToken(JwtDto<UUID> dto) {
    Date atExp = createTokenExpires(this.accessTokenExpires);
    String accessToken = generateToken(dto.toClaims(), atExp);

    Date rtExp = createTokenExpires(this.refreshTokenExpires);
    String refreshToken = generateToken(dto.toClaims(), rtExp);

    LocalDateTime accessTokenExpires = atExp.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime refreshTokenExpires = rtExp.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return Token.builder()
        .userId(dto.getId())
        .accessToken(accessToken)
        .accessTokenExpires(accessTokenExpires)
        .refreshToken(refreshToken)
        .refreshTokenExpires(refreshTokenExpires)
        .build();
  }

  private String generateToken(Map<String, Object> claims, Date expires) {
    return Jwts.builder()
        .setHeader(createHeader())
        .setClaims(claims)
        .setIssuer(issuer)
        .signWith(SignatureAlgorithm.HS256, createSignature())
        .setExpiration(expires)
        .compact();
  }

  private Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "JWT");
    header.put("alg", "HS256");
    header.put("createdAt", System.currentTimeMillis());

    return header;
  }

  private Date createTokenExpires(long expires) {
    Instant now = Instant.now();
    return Date.from(now.plusSeconds(expires));
  }

  private Key createSignature() {
    byte[] secretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(secretBytes);
  }
}
