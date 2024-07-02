package com.api.portal_exame_backend.services;

import java.util.Set;

import com.api.portal_exame_backend.model.Usuario;
import com.api.portal_exame_backend.model.UsuarioRole;

public interface UsuarioService {
    
    public Usuario criarUsuario(Usuario usuario, Set<UsuarioRole> usuarioRoles) throws Exception;

    public Usuario obterUsuario(String username);

    public void removerUsuario(Long id);
}
