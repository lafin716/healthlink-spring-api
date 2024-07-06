package com.lafin.healthlink.domain.user.dto;

import com.lafin.healthlink.domain.user.UserRole;
import com.lafin.healthlink.domain.user.UserType;
import com.lafin.healthlink.shared.util.ValidateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Builder
@ToString
public class CreateUserDto {
  private String name;
  private String email;
  private UserType userType;
  private UserRole userRole;
  private String password;
  private String passwordConfirm;

  public boolean isValid() {
    boolean isBlankParams = ValidateUtil.isBlank(
        name, email, password, passwordConfirm
    );
    if (isBlankParams) {
      return false;
    }

    return password.equals(passwordConfirm);
  }
}
