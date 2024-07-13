package com.api.portal_exame_backend.configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/* Essa classe com uma explicação resumida seria para criar um token JWT */

@Component
public class JwtUtils {

    private String SECRET_KEY = "examportal";       // chave secreta usada para assinar e verificar os tokens JWT. Vale ressaltar que essa chave tem que ser armazenada em um local seguro.

    /* Esse método vai extrair o nome de usuário do token JWT. */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /* Esse método vai Extrair a data de expiração do token JWT. */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /* Esse método vai extrair um claim específico do token usando uma função resolutora. */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /* Esse método vai extrair todos os claims do token JWT. Isso inclui informações como o sujeito (usuário), a data de expiração, etc. */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /* Esse método verifica se o token JWT expirou. */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /* Esse método vai gerar um novo token JWT para um usuário. Utiliza o nome de usuário e uma data de expiração padrão (10 horas a partir da geração). */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /* Esse método vai criar um novo token JWT com os claims fornecidos e o sujeito especificado (nome de usuário). Define a data de emissão e a data de expiração do token, e o assina com a chave secreta usando o algoritmo HS256.*/
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))          // duração de tempo de expiração para 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /* Esse método vai validar um token JWT verificando se o nome de usuário corresponde e se o token não expirou.*/
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

/* A classe 'JwtUtils' é uma classe utilitária que lida com as operações relacionadas a JSON WEB Tokens (JWTs), ela facilita a criação, validação
 * e extração de informações JWTs. Ou seja, essa classe 'JwtUtils' é utilizada em um serviço de autenticação para criar tokens JWT quando um 
 * usuário faz login (username e password) com sucesso, e para validar esses tokens em requisições subsequentes para assegurar que o usuário 
 * está autenticado.
 * 
 * Como funciona os métodos em relação a extração de informações, geração de tokens e validação de tokens. Nessa ordem:
 * 
 * 1. Extração de Informações:
 * Os métodos extractUsername, extractExpiration e extractClaim utilizam a biblioteca io.jsonwebtoken.Jwts para analisar o token JWT e extrair 
 * informações específicas.
 * 
 * 2. Geração de Tokens:
 * O método generateToken cria um mapa vazio de claims e chama createToken, que define o sujeito (nome de usuário), a data de emissão (agora) e 
 * a data de expiração (10 horas a partir de agora), e então assina o token com a chave secreta.
 * 
 * 3. Validação de Tokens:
 * O método validateToken verifica se o nome de usuário extraído do token corresponde ao nome de usuário do UserDetails fornecido e se o token 
 * não expirou.
 * 
 * Resumo:
 * -> JwtUtils é um utilitário para operações relacionadas a JWTs, como criação, validação e extração de informações.
 * -> Usa uma chave secreta (SECRET_KEY) para assinar e verificar tokens.
 * -> Gera tokens com uma data de expiração padrão de 10 horas.
 * -> Valida tokens verificando o nome de usuário e a data de expiração.
 * -> Utilizado em serviços de autenticação para gerenciar tokens JWT em aplicações Spring.
*/