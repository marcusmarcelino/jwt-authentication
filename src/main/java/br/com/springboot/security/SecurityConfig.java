package br.com.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/public/users/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated());

    http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
