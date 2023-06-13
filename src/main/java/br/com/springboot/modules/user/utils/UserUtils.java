package br.com.springboot.modules.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtils {
  public static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
