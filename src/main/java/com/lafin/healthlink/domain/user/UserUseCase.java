package com.lafin.healthlink.domain.user;

import com.lafin.healthlink.domain.user.dto.CreateUserDto;

public interface UserUseCase {
  User create(CreateUserDto dto);
}
