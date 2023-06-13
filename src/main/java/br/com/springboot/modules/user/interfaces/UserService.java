package br.com.springboot.modules.user.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.springboot.modules.user.models.User;

public interface UserService {
  List<User> getAll();

  User getById(UUID id);

  default List<User> findUnderageUsers(Integer maxAge) {
    return new ArrayList<>();
  }
}
