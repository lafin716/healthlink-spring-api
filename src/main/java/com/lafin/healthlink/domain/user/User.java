package com.lafin.healthlink.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lafin.healthlink.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String userName;
  private String userEmail;
  @Column(columnDefinition = "TEXT")
  private String userPassword;
  @Enumerated(EnumType.STRING)
  private UserStatus userStatus;
  @Enumerated(EnumType.STRING)
  private UserType userType;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;
  private int failCount;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastSignedIn;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastFailedIn;

  public boolean isOverFailCount() {
    final int limitCount = 5;
    final int recoverySecond = 60;

    // 로그인 실패 횟수 초과 안함
    if (this.failCount < limitCount) {
      return false;
    }

    // 초과한 경우 에도 마지막 실패 시간이 복구 시간 이후면 초기화
    if (LocalDateTime.now().minusSeconds(recoverySecond).isBefore(lastFailedIn)) {
      this.failCount = 0;
      return false;
    }

    return true;
  }

  public void addFailCount() {
    this.failCount++;
    this.lastFailedIn = LocalDateTime.now();
  }

  public void successLogin() {
    this.failCount = 0;
    this.lastSignedIn = LocalDateTime.now();
  }
}
