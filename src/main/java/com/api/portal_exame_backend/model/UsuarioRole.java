package com.api.portal_exame_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioRole {

    // Essa classe vai armazenar os ids de relacionamento do UsuarioRole, Usuario e Role

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRoleId;

    // Muitos UsuarioRole para um Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Muitos UsuarioRole para um Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Construtores
    public UsuarioRole() {
    }

    public UsuarioRole(Long usuarioRoleId, Usuario usuario, Role role) {
        this.usuarioRoleId = usuarioRoleId;
        this.usuario = usuario;
        this.role = role;
    }

    // Métodos Getters e Setters
    public Long getUsuarioRoleId() {
        return usuarioRoleId;
    }

    public void setUsuarioRoleId(Long usuarioRoleId) {
        this.usuarioRoleId = usuarioRoleId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
