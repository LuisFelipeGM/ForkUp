package com.fiap.forkup.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados para fazer a autenticação no sistema")
public class LoginVO {

    @NotNull(message = "Login é obrigatório")
    @Schema(description = "Login para autenticacao", example = "joao.silva")
    private String login;

    @NotNull(message = "Senha é obrigatória no cadastro")
    @Schema(description = "Senha para autenticacao", example = "SenhaSegura123!")
    private String senha;

}
