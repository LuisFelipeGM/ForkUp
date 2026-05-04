package com.fiap.forkup.exception.handler;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;

public class EnderecoNaoEncontradoException extends BusinessException{

    public EnderecoNaoEncontradoException() {
        super("Endereço não encontrado", HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND);
    }

}
