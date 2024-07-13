package com.api.portal_exame_backend.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
    
}

/* Interface GrantedAuthority - representa a autoridade concedida a um usuário, ou seja, a uma autoridade que é uma permissão (ou papel) ou 
 * um direito associado a um usuário, no caso, pode ser um papel (role) como ROLE_ADMIN ou ROLE_USER ou uma permissão específica como 
 * READ_PRIVILEGES.
 * 
 * O método getAuthority() é um método sobreescrito da interface GrantedAuthority. Ele retorna o valor da autoridade armazenada no campo 
 * authority, ou seja, obtêm o nome da autoridade atribuída a um usuário.
 * 
 * Vai precisar criar o Userdetails para que o usuário possa ser configurado com um nome de usuário, senha e uma lista de autoridades.
*/