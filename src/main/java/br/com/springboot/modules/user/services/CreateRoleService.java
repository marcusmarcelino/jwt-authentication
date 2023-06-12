package br.com.springboot.modules.user.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.repository.RoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateRoleService {
  @Autowired
  private RoleRepository repository;

  public Role execute(Role role) {
    Optional<Role> roleExists = repository.findByName(role.getName());

    if (roleExists.isPresent())
      throw new Error("Role already exists!");

    return repository.save(role);
  }
}
