package com.lafin.healthlink.presentation.api.base;

import com.lafin.healthlink.shared.exception.HealthLinkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class BaseApiAdvice {
  private Logger logger = Logger.getLogger(BaseApiAdvice.class.getName());

  @ExceptionHandler(HealthLinkException.class)
  public ResponseEntity<BaseApiAdvice> businessError(HealthLinkException exception) {
    logger.warning(exception.getMessage());
    return BaseResponse
        .fail(exception.getMessage())
        .response();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseApiAdvice> allError(Exception exception) {
    logger.warning(exception.getMessage());
    return BaseResponse
        .fail(exception.getMessage())
        .response();
  }
}
