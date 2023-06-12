package br.com.springboot.modules.user.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.dto.CreateUserRoleDTO;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateUserService {
  @Autowired
  private UserRepository repository;

  public CreateUserRoleDTO execute(User user) {
    Optional<User> existsUser = repository.findByUsername(user.getUsername());

    if (existsUser.isPresent())
      throw new Error("User already exists!");

    User createdUser = repository.save(user);

    return new CreateUserRoleDTO()
        .withUsername(createdUser.getUsername())
        .withIdUser(createdUser.getId());
  }
}