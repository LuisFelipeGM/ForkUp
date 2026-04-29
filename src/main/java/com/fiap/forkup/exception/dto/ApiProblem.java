package com.fiap.forkup.exception.dto;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhe do problema (RFC 7807) com campos extras usados pela API")
public class ApiProblem {

    @Schema(description = "URI que identifica o tipo de problema", example = "https://example.com/resource-not-found")
    private String type;

    @Schema(description = "Título do problema", example = "Erro de regra de negócio")
    private String title;

    @Schema(description = "Status HTTP", example = "404")
    private Integer status;

    @Schema(description = "Detalhe do erro", example = "Usuário não encontrado")
    private String detail;

    @Schema(description = "Instância/URI do recurso que causou o erro", example = "/api/v1/usuarios/1")
    private String instance;

    @Schema(description = "Timestamp do erro no servidor", example = "2026-04-28T22:52:04.1920341")
    private LocalDateTime timestamp;

    @Schema(description = "Código de erro interno", example = "RESOURCE_NOT_FOUND", implementation = ErrorCode.class)
    private String errorCode;

    @Schema(description = "Detalhes de campos quando aplicável")
    private List<FieldErrorDetail> fields;

}

