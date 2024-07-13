package com.api.portal_exame_backend.model;

public class JwtRequest {

    private String username;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

}

/* Essa classe JwtRequest encapsula os dados de login enviados (username e password) pelo cliente para o servidor para obter um token JWT. Ou
 * seja, essa classe será usada em endpoints de autenticação. Quando o usuário tentar fazer login, ele envia um JSON contendo o username e a 
 * senha. Esse JSON é mapeado para uma instancia JwtRequest pelo framework web de aplicação. É uma classe DTO usada para encapsular os dados de 
 * login (username e password) enviados pelo cliente para o servidor. Será usada em endpoints de autenticação para receber os dados de login e
 * processar a autenticação.
*/