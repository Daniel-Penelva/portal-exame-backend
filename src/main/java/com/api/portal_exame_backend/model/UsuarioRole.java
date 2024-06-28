package com.api.portal_exame_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRoleId;

    // Construtores
    public UsuarioRole() {
    }

    public UsuarioRole(Long usuarioRoleId) {
        this.usuarioRoleId = usuarioRoleId;
    }

    // Métodos Getters e Setters
    public Long getUsuarioRoleId() {
        return usuarioRoleId;
    }

    public void setUsuarioRoleId(Long usuarioRoleId) {
        this.usuarioRoleId = usuarioRoleId;
    }

}
