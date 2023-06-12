package br.com.springboot.modules.user.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRoleDTO {
  private UUID idUser;
  private String username;
  private List<UUID> idRoles;
}
