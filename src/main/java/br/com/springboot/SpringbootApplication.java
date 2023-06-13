package br.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(
	// CreateRoleService roleService,
	// CreateAdminUser createAdminUser) {
	// return args -> {
	// roleService.execute(new Role(null, RoleTypes.ROLE_ADMIN));
	// createAdminUser.execute(new User(null, "Marcus", "marcusmarcelino", 25,
	// "password", null));
	// };
	// }
}
