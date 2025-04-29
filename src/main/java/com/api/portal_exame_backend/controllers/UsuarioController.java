package com.api.portal_exame_backend.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.portal_exame_backend.model.Role;
import com.api.portal_exame_backend.model.Usuario;
import com.api.portal_exame_backend.model.UsuarioRole;
import com.api.portal_exame_backend.services.UsuarioService;

//@CrossOrigin("*")   
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Corrigindo a associação UsuarioRole entre Usuario e Role
    /* Esse método cria um novo usuário, define seu perfil padrão, cria uma nova PERMISSÃO (ou papel) de usuário (role) e associa o usuário a esse papel USER. */
    // http://localhost:8080/usuarios/
    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody Usuario usuario) throws Exception {

        // Codifica a senha do usuário antes de salvar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuario.setPerfil("default.png");

        Set<UsuarioRole> usuRoles = new HashSet<>();

        Role role = new Role();
        //role.setRoleId(1L);
        //role.setRoleName("ADMIN");

        role.setRoleId(2L);
        role.setRoleName("USER");

        UsuarioRole usuarioRole = new UsuarioRole();               // Esse objeto representa a associação entre um usuário e um role.
        usuarioRole.setUsuario(usuario);                           // vincula o usuário criado anteriormente ao objeto de associação.
        usuarioRole.setRole(role);                                 // vincula o papel (ou permissão) USER criada anteriormente ao objeto de associação.

       usuRoles.add(usuarioRole);                                  // adiciona o objeto usuarioRole ao conjunto usuRoles.
        return usuarioService.criarUsuario(usuario, usuRoles);
    }

    // http://localhost:8080/usuarios/{username}
    @GetMapping("/{username}")
    public Usuario obterUsuario(@PathVariable("username") String username) {
        return usuarioService.obterUsuario(username);
    }

    // http://localhost:8080/usuarios/{usuarioId}
    @DeleteMapping("/{usuarioId}")
    public void removerUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.removerUsuario(usuarioId);
    }
}
