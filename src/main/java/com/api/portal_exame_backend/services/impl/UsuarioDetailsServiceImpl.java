package com.api.portal_exame_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.portal_exame_backend.model.Usuario;
import com.api.portal_exame_backend.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;   // injetar uma instância do repositório UsuarioRepository para permitir que a classe use o repositório para acessar dados de usuário

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return usuario;
    }

}

/* Essa classe "UsuarioDetailsServiceImpl" é usada para carregar os detalhes do usuário durante o processo de autenticação. Quando um usuário 
 * tenta fazer login, o Spring Security chama o método loadUserByUsername para recuperar os detalhes do usuário e verificar suas credenciais.
 * A interface "UserDetailsService" utiliza o método sobreescrito "loadUserByUsername()" que implementa o carregamento dos detalhes do usuário
 * com base no nome do usuário fornecido. Esse método é chamado pelo Spring Security durante o processo de autenticação. Aqui, o método busca 
 * um usuário no repositório UsuarioRepository pelo username de usuário. Se o usuário não for encontrado é lançada uma exceção 
 * "UsernameNotFoundException". Agora, se o usuário for encontrado, ele é retornado. 
 */