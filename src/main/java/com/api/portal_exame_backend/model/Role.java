package com.api.portal_exame_backend.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    // Essa classe vai armazenar o id e o papel role

    @Id
    private Long roleId;
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UsuarioRole> usuarioRoles = new HashSet<>();

    // Construtores
    public Role() {
    }

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(Long roleId, String roleName, Set<UsuarioRole> usuarioRoles) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.usuarioRoles = usuarioRoles;
    }

    // Métodos Getters e Setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UsuarioRole> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRole> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

}
