package com.lafin.healthlink.presentation.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.csrf((c) -> c.disable())
        .cors((c) -> c.disable())
        .formLogin((c) -> c.disable())
        .authorizeHttpRequests((req) -> {
          req.requestMatchers("/**").permitAll()
              .anyRequest().permitAll();
        })
        .build();
  }
}
