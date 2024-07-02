package com.api.portal_exame_backend.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    // Essa classe vai armazenar os dados do usuário

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private boolean enabled = true;
    private String perfil;

    // Um Usuário pode ter muitos UsuarioRole
    // Estou utilizando o FetchType.EAGER que é uma estratégia que os dados são carregados imediatamente junto com a entidade proprietaria (pai).
    // O mappedyBy="usuario" define que o usuario é o dono desse relacionamento
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRole> usuarioRoles = new HashSet<>();

    // Construtores
    public Usuario() {
    }

    public Usuario(Long id, String username, String password, String firstname, String lastname, String email,
            String phone, boolean enabled, String perfil, Set<UsuarioRole> usuarioRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.perfil = perfil;
        this.usuarioRoles = usuarioRoles;
    }

    // Métodos Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Set<UsuarioRole> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRole> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

}
