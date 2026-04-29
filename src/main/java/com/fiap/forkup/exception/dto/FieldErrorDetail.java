package com.fiap.forkup.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDetail {

    @Schema(description = "Campo com erro", example = "email")
    private String field;

    @Schema(description = "Mensagem de erro", example = "Email inválido")
    private String message;

}
