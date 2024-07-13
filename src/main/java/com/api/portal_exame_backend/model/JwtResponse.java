package com.api.portal_exame_backend.model;

public class JwtResponse {

    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

/* A classe JwtResponse encapsula o token JWT que é enviado do servidor para o cliente após uma autenticação bem-sucedida. A propriedade "token"
 * vai armazenar o JWT que será enviado para o cliente. Ou seja, Quando o cliente envia suas credenciais (por exemplo, username e password), o 
 * servidor autentica o cliente e, se as credenciais estiverem corretas, gera um token JWT e o envia de volta ao cliente. O JwtResponse é uma 
 * classe DTO usada para encapsular o token JWT que é enviado do servidor para o cliente após uma autenticação bem-sucedida, isto é, é usada 
 * em endpoints de autenticação para retornar o JWT ao cliente.
*/