package br.com.springboot.modules.user.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.modules.user.dto.AssignRoleToUserDTO;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.services.CreateRoleService;
import br.com.springboot.modules.user.services.AssignRuleToUser;
import br.com.springboot.modules.user.services.BasicUserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private BasicUserService basicUserService;

  @Autowired
  private AssignRuleToUser assignRuleToUserService;

  @Autowired
  private CreateRoleService createRoleService;

  List<User> users = new ArrayList<>();

  @GetMapping("")
  public ResponseEntity<List<User>> get() {
    return ResponseEntity.ok().body(basicUserService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable UUID id) {
    return ResponseEntity.ok().body(basicUserService.getById(id));
  }

  @GetMapping("/isNotOfAge")
  public List<User> findUnderageUsers(@RequestParam Integer maxAge) {
    List<User> users = basicUserService.findUnderageUsers(maxAge);
    return users;
  }

  @PostMapping("/role")
  public AssignRoleToUserDTO addedRole(@RequestBody AssignRoleToUserDTO createUserRoleDTO) {
    return assignRuleToUserService.execute(createUserRoleDTO);
  }

  @PostMapping("/create/role")
  public Role createRole(@RequestBody Role role) {
    return createRoleService.execute(role);
  }
}
