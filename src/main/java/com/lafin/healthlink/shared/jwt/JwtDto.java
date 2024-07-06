package com.lafin.healthlink.shared.jwt;

import java.util.Map;

public interface JwtDto<K> {
  K getId();
  Map<String, Object> toClaims();
}
