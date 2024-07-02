package com.api.portal_exame_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.portal_exame_backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
