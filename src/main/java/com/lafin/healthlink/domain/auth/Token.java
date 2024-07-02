package com.lafin.healthlink.domain.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private UUID userId;
  private String accessToken;
  private String refreshToken;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime accessTokenExpires;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime refreshTokenExpires;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
