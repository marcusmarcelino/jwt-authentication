package br.com.springboot.modules.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springboot.modules.user.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  @Query("select u from User u where u.age < :maxAge")
  public List<User> findUnderageUsers(@Param("maxAge") Integer maxAge);

  public List<User> findByAgeLessThan(Integer maxAge);

  public Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u JOIN FETCH u.roles where u.username = :username")
  public User findByUsernameFetchRoles(@Param("username") String username);
}
