package com.lafin.healthlink.domain.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lafin.healthlink.shared.entity.BaseEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tokens")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Token extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private UUID userId;
  @Column(columnDefinition = "TEXT")
  private String accessToken;
  @Column(columnDefinition = "TEXT")
  private String refreshToken;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime accessTokenExpires;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime refreshTokenExpires;

  public Token update(Token token) {
    if (!StringUtils.isBlank(token.getAccessToken())) {
      this.accessToken = token.getAccessToken();
    }

    if (Objects.nonNull(token.getAccessTokenExpires())) {
      this.accessTokenExpires = token.getAccessTokenExpires();
    }

    if (!StringUtils.isBlank(token.getRefreshToken())) {
      this.refreshToken = token.getRefreshToken();
    }

    if (Objects.nonNull(token.getRefreshTokenExpires())) {
      this.refreshTokenExpires = token.getRefreshTokenExpires();
    }

    return this;
  }
}
