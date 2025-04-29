package com.api.portal_exame_backend.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.portal_exame_backend.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return; // Importante: sair do filtro para não processar o token
        }

        // Nessa parte, obtem o Header de Autorização
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Nessa parte, verifica se o header está presente e se começa com "Bearer ". Depois extrai o token removendo o prefixo "Bearer ".
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);   // O número 7 é usado para remover os primeiros 7 caracteres ("Bearer ") e extrair apenas o token JWT.

            try {
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException exception) {
                logger.warn("O token já expirou", exception);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Erro ao extrair o username do token", e);
            }

        } else {
            logger.warn("Token inválido, não começa com bearer string");
        }

        // Nessa parte, se o usuário foi extraído e o contexto de segurança não possui autenticação, carrega os detalhes do usuário. Depois válida o token. E se o token for válido, configura a autenticação no contexto de segurança.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        } else {
            logger.warn("O token não é válido");
        }

        // Nesta parte, é a continuação da cadeia de filtros
        filterChain.doFilter(request, response);
    }
}

/* Essa classe 'JwtAuthenticationFilter' que estende 'OncePerRequestFilter' é responsável por filtrar todas as requisições HTTP para verificar 
 * se um token JWT (JSON Web Token) está presente e válido. Se o token for válido, ela autentica o usuário e configura o contexto de segurança 
 * da aplicação com os detalhes do usuário. 
 * 
 * Como vai funcionar a Classe de Filtro JWT:
 * 
 * 1. Injeção de Dependências:
 * UserDetailsService e JwtUtils são injetados para carregar os detalhes do usuário e manipular o JWT.
 * 
 * 2. Verificação e Validação do Token:
 *      -> A classe verifica se o header de autorização começa com "Bearer ".
 *      -> Extrai o token JWT.
 *      -> Tenta extrair o nome de usuário do token.
 *      -> Valida o token.
 *      -> Se o token é válido e o contexto de segurança não está autenticado, configura a autenticação no contexto de segurança.
 * 
 * 3. Propagação da Requisição:
 * Continua a cadeia de filtros chamando filterChain.doFilter.
 * 
 * A classe que a estende, "OncePerRequestFilter", garante que um filtro seja executado apenas uma vez por requisição. Isso é útil para filtros 
 * que devem realizar certas ações (como autenticação) apenas uma vez por requisição HTTP.
 * 
 * Portanto, A classe JwtAuthenticationFilter estende OncePerRequestFilter para garantir que a lógica de autenticação JWT seja executada apenas 
 * uma vez por requisição. Ela verifica a presença e validade de um token JWT, extrai informações do token e autentica o usuário se o token for 
 * válido. Utilizar OncePerRequestFilter simplifica a implementação de filtros que devem ser executados uma vez por requisição, tornando o código 
 * mais eficiente e evitando execuções desnecessárias.
*/