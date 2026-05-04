package com.fiap.forkup.validations;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.exception.dto.FieldErrorDetail;
import com.fiap.forkup.exception.handler.BusinessException;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class Validator {

    protected void existErrors(List<FieldErrorDetail> errors) throws BusinessException {

        if (!errors.isEmpty()) {
            throw new BusinessException("Erro de validação", HttpStatus.BAD_REQUEST, ErrorCode.VALIDATION_ERROR).addMetadata("fields", errors);
        }

    }

}
