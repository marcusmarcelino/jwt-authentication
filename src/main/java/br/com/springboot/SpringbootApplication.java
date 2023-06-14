package br.com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.springboot.modules.user.enums.RoleTypes;
import br.com.springboot.modules.user.models.Role;
import br.com.springboot.modules.user.models.User;
import br.com.springboot.modules.user.services.CreateAdminUser;
import br.com.springboot.modules.user.services.CreateRoleService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(
	// 		CreateRoleService roleService,
	// 		CreateAdminUser createAdminUser) {
	// 	return args -> {
	// 		roleService.execute(new Role(null, RoleTypes.ROLE_ADMIN));
	// 		createAdminUser.execute(new User(null, "admin", "admin", 25,
	// 				"password", null));
	// 	};
	// }
}
