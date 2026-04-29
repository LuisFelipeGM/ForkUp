package com.fiap.forkup.exception.handler;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BusinessException extends Exception {

    private final HttpStatus status;
    private final ErrorCode errorCode;
    private final Map<String, Object> metadata;

    public BusinessException(String message, HttpStatus status, ErrorCode errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.metadata = new HashMap<>();
    }

    public BusinessException addMetadata(String key, Object value) {
        this.metadata.put(key, value);
        return this;
    }

}
