package com.lafin.healthlink.shared.util;

import java.util.Objects;

public class ValidateUtil {

  public static boolean isBlank(String ...strings) {
    for (String s : strings) {
      if (Objects.isNull(s) || s.isBlank()) {
        return true;
      }
    }

    return false;
  }
}
