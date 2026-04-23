package com.fiap.forkup.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends BusinessException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

}
