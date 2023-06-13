package br.com.springboot.modules.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.modules.user.interfaces.UserService;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class BasicUserService implements UserService {

  @Autowired
  private UserRepository repository;

  @Override
  public List<User> getAll() {
    return repository.findAll();
  }

  @Override
  public User getById(UUID id) {
    User user = repository.findById(id).orElseThrow(() -> {
      throw new Error("Usuário não encontrado!");
    });
    return user;
  }

  @Override
  public List<User> findUnderageUsers(Integer maxAge) {
    return repository.findByAgeLessThan(maxAge);

  }
}
