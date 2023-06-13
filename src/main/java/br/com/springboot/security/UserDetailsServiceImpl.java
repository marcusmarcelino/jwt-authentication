package br.com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameFetchRoles(username);
    if (user == null)
      throw new Error("Usuário não cadastrado na base de dados!");

    return UserPrincipal.create(user);
  }

}
