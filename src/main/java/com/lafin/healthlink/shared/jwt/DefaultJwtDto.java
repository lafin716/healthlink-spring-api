package com.lafin.healthlink.shared.jwt;

import com.lafin.healthlink.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
public class DefaultJwtDto implements JwtDto<UUID> {
  private UUID userId;
  private String userEmail;

  public static DefaultJwtDto createJwtDto(User user) {
    return DefaultJwtDto.builder()
        .userId(user.getId())
        .userEmail(user.getUserEmail())
        .build();
  }

  @Override
  public UUID getId() {
    return userId;
  }

  @Override
  public Map<String, Object> toClaims() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("userEmail", userEmail);

    return claims;
  }
}
