package com.api.portal_exame_backend.exception;

public class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException() {
        super("O usuário com esse username não existe no banco de dados");
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }

}
