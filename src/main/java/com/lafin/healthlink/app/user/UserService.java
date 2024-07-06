package com.lafin.healthlink.app.user;

import com.lafin.healthlink.domain.user.*;
import com.lafin.healthlink.domain.user.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;


  @Override
  public User create(CreateUserDto dto) {
    if (!dto.isValid()) {
      throw UserExceptionHolder.NOT_VALID_USER_INPUT;
    }

    var foundUser = userRepository.findUserByUserEmail(dto.getEmail());
    if (foundUser.isPresent()) {
      throw UserExceptionHolder.DUPLICATED_USER;
    }

    var newUser = User.builder()
        .userName(dto.getName())
        .userEmail(dto.getEmail())
        .userPassword(passwordEncoder.encode(dto.getPassword()))
        .userType(UserType.EMAIL)
        .userRole(UserRole.ADMIN)
        .userStatus(UserStatus.ACTIVE)
        .failCount(0)
        .build();
    return userRepository.save(newUser);
  }
}
