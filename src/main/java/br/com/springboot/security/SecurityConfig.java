package br.com.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  // Essa configuração deve ser utilizada sem userdetailService estar implementado
  // do contrário, ocorrerá erro na execução do teste, já que por sua vez
  // ele utilizará as configurações do bean do userDetailService
  // o qual possui maior prioridade. Ou seja, deve-se escolher qual o tipo de
  // configuração
  // irá utilizar
  // @Bean
  // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder
  // passwordEncoder) {
  // UserDetails user = User.withUsername("user")
  // .password(passwordEncoder.encode("password"))
  // .roles("CUSTOMER")
  // .build();

  // UserDetails company = User.withUsername("company")
  // .password(passwordEncoder.encode("password"))
  // .roles("COMPANY")
  // .build();

  // UserDetails admin = User.withUsername("admin")
  // .password(passwordEncoder.encode("password"))
  // .roles("USER", "COMPANY", "ADMIN")
  // .build();

  // return new InMemoryUserDetailsManager(user, company, admin);
  // }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/users/**").hasRole("ADMIN")
            .requestMatchers("/public/users/**").permitAll()
            .anyRequest().authenticated())
        .httpBasic(withDefaults());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
