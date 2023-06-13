package br.com.springboot.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.modules.user.dto.AssignRoleToUserDTO;
import br.com.springboot.modules.user.enums.RoleTypes;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.services.CreateUserService;

@RestController
@RequestMapping("/public/users")
public class PublicUserController {
  @Autowired
  private CreateUserService createUserService;

  @PostMapping("/register/customer")
  public ResponseEntity<AssignRoleToUserDTO> registerCustomer(@RequestBody User user) {
    return ResponseEntity.ok().body(createUserService.execute(user, RoleTypes.ROLE_CUSTOMER));
  }

  @PostMapping("/register/company")
  public ResponseEntity<AssignRoleToUserDTO> registerCompany(@RequestBody User user) {
    return ResponseEntity.ok().body(createUserService.execute(user, RoleTypes.ROLE_COMPANY));
  }
}
