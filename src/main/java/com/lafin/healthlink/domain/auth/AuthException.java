package com.lafin.healthlink.domain.auth;

import com.lafin.healthlink.shared.exception.HealthLinkException;

public class AuthException extends HealthLinkException {
  public AuthException(String message) {
    super(message);
  }
}
