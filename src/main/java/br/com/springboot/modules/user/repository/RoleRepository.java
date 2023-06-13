package br.com.springboot.modules.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.enums.RoleTypes;

public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByRoleName(RoleTypes roleName);
}
