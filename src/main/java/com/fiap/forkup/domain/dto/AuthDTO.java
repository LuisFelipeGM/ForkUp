package com.fiap.forkup.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "DTO utilizado quando login feito com sucesso")
public class AuthDTO {

    @Schema(description = "Mensagem de sucesso no Login", example = "Login realizado com sucesso")
    private String mensagem;

    public AuthDTO() {
        this.mensagem = "Login realizado com sucesso";
    }
}
