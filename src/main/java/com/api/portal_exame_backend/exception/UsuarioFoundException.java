package com.api.portal_exame_backend.exception;

public class UsuarioFoundException extends Exception {

    public UsuarioFoundException() {
        super("O usuário com esse username já existe no banco de dados");
    }

    public UsuarioFoundException(String message) {
        super(message);
    }

}
