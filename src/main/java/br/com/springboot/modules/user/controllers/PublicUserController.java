package br.com.springboot.modules.user.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicUserController {

  @GetMapping("")
  public ResponseEntity<String> getText() {
    return ResponseEntity.ok().body("Esse é um método teste que deve ser liberado somente com autenticação");
  }
}
