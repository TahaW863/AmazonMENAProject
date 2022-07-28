package com.taha.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class AuthApplication {
  private static PasswordEncoder passwordEncoder;
  public AuthApplication(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
    log.info("passwordEncoder: {}", passwordEncoder.encode("123456"));
  }
}
