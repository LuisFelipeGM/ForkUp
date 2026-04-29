package com.fiap.forkup.exception.handler;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends BusinessException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado", HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND);
    }

}
