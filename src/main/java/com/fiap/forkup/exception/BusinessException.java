package com.fiap.forkup.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class BusinessException extends Exception {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
