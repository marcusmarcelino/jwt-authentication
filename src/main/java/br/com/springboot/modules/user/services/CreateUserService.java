package br.com.springboot.modules.user.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.dto.AssignRoleToUserDTO;
import br.com.springboot.modules.user.enums.RoleTypes;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.RoleRepository;
import br.com.springboot.modules.user.repository.UserRepository;
import br.com.springboot.modules.user.utils.UserUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateUserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;

  public AssignRoleToUserDTO execute(User user, RoleTypes type) {
    Optional<User> existsUser = repository.findByUsername(user.getUsername());

    if (existsUser.isPresent())
      throw new Error("O usuário já existe!");

    Optional<Role> roleFound = roleRepository.findByRoleName(type);

    roleFound.ifPresentOrElse((role) -> user.setRoles(Arrays.asList(role)), () -> {
      throw new Error("Erro na atribuição de regras ao usuário");
    });

    user.setPassword(UserUtils.passwordEncoder().encode(user.getPassword()));

    User createdUser = repository.save(user);

    return new AssignRoleToUserDTO()
        .withUsername(createdUser.getUsername())
        .withIdUser(createdUser.getId())
        .withIdRoles(createdUser.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
  }
}
