package com.api.portal_exame_backend.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.portal_exame_backend.model.Usuario;
import com.api.portal_exame_backend.model.UsuarioRole;
import com.api.portal_exame_backend.repositories.RoleRepository;
import com.api.portal_exame_backend.repositories.UsuarioRepository;
import com.api.portal_exame_backend.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Usuario criarUsuario(Usuario usuario, Set<UsuarioRole> usuarioRoles) throws Exception {
        
        Usuario novoUsuario = usuarioRepository.findByUsername(usuario.getUsername());
        if (novoUsuario != null) {
            System.out.println("Usuário já existe");
            throw new Exception("Usuário já existe");
        } else {
            for (UsuarioRole usuRole : usuarioRoles) {
                roleRepository.save(usuRole.getRole());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            novoUsuario = usuarioRepository.save(usuario);
        }
        return novoUsuario;
    }

    @Override
    public Usuario obterUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
