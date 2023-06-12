package br.com.springboot.modules.user.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.modules.user.dto.CreateUserRoleDTO;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.services.CreateRoleService;
import br.com.springboot.modules.user.services.AssignRuleToUser;
import br.com.springboot.modules.user.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserServiceImpl service;

  @Autowired
  private AssignRuleToUser createRoleUserService;

  @Autowired
  private CreateRoleService createRoleService;

  List<User> users = new ArrayList<>();

  @GetMapping("")
  public List<User> get() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable UUID id) {
    Optional<User> user = service.getById(id);
    return user.orElse(null);
  }

  @GetMapping("/isNotOfAge")
  public List<User> findUnderageUsers(@RequestParam Integer maxAge) {
    List<User> users = service.findUnderageUsers(maxAge);
    return users;
  }

  @PostMapping("/role")
  public CreateUserRoleDTO addedRole(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
    return createRoleUserService.execute(createUserRoleDTO);
  }

  @PostMapping("/create/role")
  public Role createRole(@RequestBody Role role) {
    return createRoleService.execute(role);
  }
}
