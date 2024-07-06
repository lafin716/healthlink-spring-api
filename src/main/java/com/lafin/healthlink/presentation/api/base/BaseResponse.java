package com.lafin.healthlink.presentation.api.base;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
@ToString
public class BaseResponse<T> {
  private boolean result;
  private String message;
  private T data;

  public static BaseResponse ok(Object data) {
    return BaseResponse.builder()
        .result(true)
        .message("정상 요청 되었습니다")
        .data(data)
        .build();
  }

  public static BaseResponse fail(String message) {
    return BaseResponse.builder()
        .result(false)
        .message(message)
        .build();
  }

  public ResponseEntity<BaseResponse<T>> response() {
    return ResponseEntity.ok(this);
  }
}
