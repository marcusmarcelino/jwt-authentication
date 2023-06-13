package br.com.springboot.modules.user.models;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import br.com.springboot.modules.user.enums.RoleTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  private RoleTypes roleName;

  public Role(UUID id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return roleName.toString();
  }
}
