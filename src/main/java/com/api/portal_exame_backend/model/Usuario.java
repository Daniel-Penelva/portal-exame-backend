package com.api.portal_exame_backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario implements UserDetails{

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

    // Implementações dos métodos UserDetails, tb inclui os métodos getUsername() e getPassword()
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Set<Authority> autoridades = new HashSet<>();
        this.usuarioRoles.forEach(usuarioRole -> {
            autoridades.add(new Authority(usuarioRole.getRole().getRoleName()));
        });
        return autoridades;
    }

    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

/* A interface UserDetails fornece as informações essenciais sobre um usuário, que são usadas pelo Spring Security para autenticação e 
 * autorização. Com essa interface o Spring Security trabalha armazenando os usuários. Sobre os métodos sobreescritos:
 * 
 * 1. Collection<? extends GrantedAuthority> getAuthorities():
 * Retorna as autoridades concedidas ao usuário. Essas autoridades são representadas como roles (papéis) ou permissões.
 * 
 * 2. String getPassword():
 * Retorna a senha do usuário.
 * 
 * 3. String getUsername():
 * Retorna o nome de usuário usado para autenticação.
 * 
 * 4. boolean isAccountNonExpired():
 * Indica se a conta do usuário expirou. Uma conta expirada não pode ser usada para autenticação.
 * 
 * 5. boolean isAccountNonLocked():
 *  Indica se a conta do usuário está bloqueada. Uma conta bloqueada não pode ser usada para autenticação.
 * 
 * 6. boolean isCredentialsNonExpired():
 * Indica se as credenciais do usuário (senha) expiraram. Credenciais expiradas impedem a autenticação.
 * 
 * 7. boolean isEnabled():
 * Indica se o usuário está habilitado. Um usuário desabilitado não pode ser autenticado.
 * 
*/
