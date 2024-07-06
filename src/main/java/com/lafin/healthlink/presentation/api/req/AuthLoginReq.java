package com.lafin.healthlink.presentation.api.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthLoginReq {
  private String email;
  private String password;
}
