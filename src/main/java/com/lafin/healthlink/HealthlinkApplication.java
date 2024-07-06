package com.lafin.healthlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HealthlinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(HealthlinkApplication.class, args);
  }

}
