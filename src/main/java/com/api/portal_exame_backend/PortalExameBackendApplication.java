package com.api.portal_exame_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalExameBackendApplication implements CommandLineRunner {

	/* 
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
    private PasswordEncoder passwordEncoder;
	*/

	public static void main(String[] args) {
		SpringApplication.run(PortalExameBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/* try{
		Usuario usuario = new Usuario();
		usuario.setFirstname("Biana");
		usuario.setLastname("Penelva");
		usuario.setUsername("bi4.penelva");
		usuario.setPassword(passwordEncoder.encode("123"));
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
	} catch (UsuarioFoundException exception){
			exception.printStackTrace();
	}
*/
	}

}
