package com.api.portal_exame_backend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.portal_exame_backend.model.Role;
import com.api.portal_exame_backend.model.Usuario;
import com.api.portal_exame_backend.model.UsuarioRole;
import com.api.portal_exame_backend.services.UsuarioService;

@SpringBootApplication
public class PortalExameBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(PortalExameBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* 
		Usuario usuario = new Usuario();
		usuario.setFirstname("Biana");
		usuario.setLastname("Penelva");
		usuario.setUsername("bi4.penelva");
		usuario.setPassword("123");
		usuario.setEmail("biana@gmail.com");
		usuario.setPhone("21993445566");
		usuario.setPerfil("foto.png");

		Role role = new Role();
		role.setRoleId(2L);
		role.setRoleName("USER");

		Set<UsuarioRole> usuarioRoles = new HashSet<>();

		UsuarioRole usuRole = new UsuarioRole();
		usuRole.setRole(role);
		usuRole.setUsuario(usuario);

		usuarioRoles.add(usuRole);

		Usuario criaUsuario = usuarioService.criarUsuario(usuario, usuarioRoles);

		System.out.println(criaUsuario.getUsername());
		*/
	}

}
