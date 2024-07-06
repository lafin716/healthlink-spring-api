package com.lafin.healthlink.domain.user;

import com.lafin.healthlink.shared.exception.HealthLinkException;

public class UserException extends HealthLinkException {
  public UserException(String message) {
    super(message);
  }
}
