package br.com.springboot.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.modules.user.dto.AssignRoleToUserDTO;
import br.com.springboot.modules.user.enums.UserType;
import br.com.springboot.modules.user.models.JWTToken;
import br.com.springboot.modules.user.models.Credentials;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.services.CreateUserService;
import br.com.springboot.security.TokenUtil;

@RestController
@RequestMapping("/public/users")
public class PublicUserController {
  @Autowired
  private CreateUserService createUserService;

  @PostMapping("/register/customer")
  public ResponseEntity<AssignRoleToUserDTO> registerCustomer(@RequestBody User user) {
    return ResponseEntity.ok().body(createUserService.execute(user, UserType.CUSTOMER));
  }

  @PostMapping("/register/company")
  public ResponseEntity<AssignRoleToUserDTO> registerCompany(@RequestBody User user) {
    return ResponseEntity.ok().body(createUserService.execute(user, UserType.COMPANY));
  }

  @PostMapping("/login")
  public ResponseEntity<JWTToken> login(@RequestBody Credentials credentials) {
    if (credentials.getUsername().equals("marcusmarcelino") && credentials.getPassword().equals("54321")) {
      return ResponseEntity.ok().body(TokenUtil.encodeToken(credentials));
    }
    return ResponseEntity.status(403).build();
  }
}
