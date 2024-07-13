package com.api.portal_exame_backend.configuration;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component                                                                     // Lembrete: A anotação @Component registra esta classe como um bean no contexto do Spring, tornando-a disponível para ser injetada em outras partes da aplicação.
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado");    // envia um código de status HTTP 401 (Não Autorizado) junto com a mensagem "Usuário não autorizado".
    }
    
}

/* Essa classe 'JwtAuthenticationEntryPoint' implementa a interface 'AuthenticationEntryPoint' que é usada para lidar com tentativas de acesso  
 * não autorizado a recursos protegidos. Quando uma requisição não autenticada é feita a um endpoint que requer autenticação, o 
 * AuthenticationEntryPoint é invocado.
 * 
 * A interface 'AuthenticationEntryPoint' tem um único método, commence, que é chamado quando uma requisição não autenticada tenta acessar um 
 * recurso protegido. Ao implementar a interface AuthenticationEntryPoint, ela permite que controle a mensagem de erro e o código de status 
 * retornados ao cliente, garantindo uma resposta adequada a requisições não autenticadas.*/