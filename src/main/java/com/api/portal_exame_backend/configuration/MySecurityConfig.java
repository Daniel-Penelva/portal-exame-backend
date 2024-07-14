package com.api.portal_exame_backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.portal_exame_backend.services.impl.UserDetailsServiceImpl;

@Configuration                                                             // Lembrete: Essa anotação indica que é uma classe de configuração do Spring e pode definir beans.
@EnableWebSecurity                                                         // Habilita a segurança web no Spring Security.
@EnableMethodSecurity(prePostEnabled = true)                               // Habilita a segurança a nível de método.
public class MySecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;                // Trata tentativas de acesso não autorizadas.

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;                  // Carrega os detalhes do usuário (como credenciais e autorizações).

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;                 // Filtra as requisições HTTP para validar os tokens JWT.


    /*Esse método define o AuthenticationManager para ser utilizado na autenticação. Isso é necessário para autenticar usuários durante o login.*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*Esse método define o PasswordEncoder que será utilizado para codificar e verificar as senhas dos usuários. Aqui, está utilizando o BCryptPasswordEncoder.*/ 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Esse método configura o DaoAuthenticationProvider com o UserDetailsService e PasswordEncoder personalizados. Este provedor de autenticação 
    é responsável por realizar a autenticação com base nos detalhes do usuário.*/
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /*Esse método configura a cadeia de filtros de segurança.*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())                                                         // desabilita CSRF e CORS
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/generate-token", "/usuarios/").permitAll()     // regras de autorização que define as URLs que são permitidas sem autenticação
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated()                                                 // exige autenticação para qualquer outra requisição.
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint(unauthorizedHandler)                                 // tratamentos de exceção para tratar tentativas de acesso não autorizadas.
            )
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)                        // gerenciamento de sessão que define a política de criação de sessão como STATELESS, uma vez que estou usando JWTs para gerenciar sessões.
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);   // filtros de autenticação para garantir que as requisições sejam verificadas quanto à presença e validade de um token JWT.

        return http.build();
    }

}

/* Essa classe é uma configuração de segurança personalizada que utiliza autenticação baseada em JWT (JSON WEB TOKEN). 
 * Aspectos Essenciais:
 * 
 *  -> Configuração de autenticação e gerenciamento de senhas.
 *  -> Autorização de requisições baseadas em roles e permissões.
 *  -> Filtragem de requisições para validar tokens JWT.
 *  -> Tratamento de exceções de segurança.
*/