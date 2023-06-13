package br.com.springboot.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.springboot.modules.user.models.User;

public class UserPrincipal implements UserDetails {
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = user.getRoles();
  }

  public static UserPrincipal create(User user) {
    return new UserPrincipal(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
