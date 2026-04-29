package com.fiap.forkup.exception.factory;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.exception.handler.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;

public class ProblemDetailFactory {

    public static ProblemDetail create(HttpStatus httpStatus, String Title, String detail, String path, ErrorCode errorCode) {

        ProblemDetail problem = ProblemDetail.forStatus(httpStatus);
        problem.setTitle(Title);
        problem.setDetail(detail);
        problem.setInstance(URI.create(path));
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("errorCode", errorCode.name());

        return problem;
    }

    public static ProblemDetail fromBusinessException(BusinessException ex, String path) {
        ProblemDetail problem = create(ex.getStatus(), "Erro de regra de negócio", ex.getMessage(), path, ex.getErrorCode());

        ex.getMetadata().forEach(problem::setProperty);

        return problem;
    }

}
