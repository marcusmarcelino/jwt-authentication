package br.com.springboot.modules.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.dto.CreateUserRoleDTO;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.UserRepository;

@Service
public class CreateRoleUserService {
  @Autowired
  private UserRepository repository;

  public CreateUserRoleDTO execute(CreateUserRoleDTO createUserRoleDTO) {
    Optional<User> userExists = repository.findById(createUserRoleDTO.getIdUser());
    List<Role> roles = new ArrayList<>();

    if (userExists.isEmpty()) {
      throw new Error("User does not exists!");
    }

    roles = createUserRoleDTO.getIdRoles().stream().map(role -> new Role(role)).collect(Collectors.toList());

    User user = userExists.get();

    user.setRoles(roles);

    repository.save(user);

    return new CreateUserRoleDTO()
        .withIdUser(user.getId())
        .withIdRoles(user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
  }
}
