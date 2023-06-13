package br.com.springboot.modules.user.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.enums.RoleTypes;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.RoleRepository;
import br.com.springboot.modules.user.repository.UserRepository;
import br.com.springboot.modules.user.utils.UserUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateAdminUser {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public void execute(User user) {
    Optional<User> existsUser = userRepository.findByUsername(user.getUsername());

    if (existsUser.isPresent())
      throw new Error("Usuário já cadastrado!");

    Optional<Role> roleFound = roleRepository.findByRoleName(RoleTypes.ROLE_ADMIN);

    roleFound.ifPresentOrElse(role -> user.setRoles(Arrays.asList(role)), () -> {
      throw new Error("Erro na atribuição de regras ao usuário");
    });

    user.setPassword(UserUtils.passwordEncoder().encode(user.getPassword()));

    userRepository.save(user);
  }
}
