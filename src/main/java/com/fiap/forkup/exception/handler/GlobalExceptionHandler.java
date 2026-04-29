package com.fiap.forkup.exception.handler;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.exception.dto.FieldErrorDetail;
import com.fiap.forkup.exception.factory.ProblemDetailFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {
        return ProblemDetailFactory.fromBusinessException(
                ex,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetailFactory.create(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                "Um ou mais campos estão inválidos",
                request.getRequestURI(),
                ErrorCode.VALIDATION_ERROR
        );

        List<FieldErrorDetail> fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new FieldErrorDetail(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        problem.setProperty("fields", fields);

        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {

        log.error("Erro inesperado", ex);

        return ProblemDetailFactory.create(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno",
                "Ocorreu um erro inesperado",
                request.getRequestURI(),
                ErrorCode.INTERNAL_ERROR
        );
    }


}



