package com.lafin.healthlink.presentation.api.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthRefreshReq {
  private String accessToken;
  private String refreshToken;
}
