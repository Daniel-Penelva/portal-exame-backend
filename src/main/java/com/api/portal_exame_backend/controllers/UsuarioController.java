package com.api.portal_exame_backend.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // http://localhost:8080/usuarios/
    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody Usuario usuario) throws Exception {

        Set<UsuarioRole> usuRoles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("USER");

        UsuarioRole usuarioRole = new UsuarioRole();
        usuarioRole.setUsuario(usuario);
        usuarioRole.setRole(role);

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
