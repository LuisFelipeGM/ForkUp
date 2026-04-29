package com.fiap.forkup.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Dados para alteracao de senha")
public class AlterarSenhaVO {

    @NotBlank(message = "Senha atual é obrigatória")
    @Schema(description = "Senha atual do usuario", example = "SenhaAtual123!")
    private String senhaAtual;

    @NotBlank(message = "Nova senha é obrigatória")
    @Schema(description = "Nova senha desejada", example = "NovaSenha456!")
    private String novaSenha;

    @NotBlank(message = "Confirmação de Senha é obrigatória")
    @Schema(description = "Confirmacao da nova senha", example = "NovaSenha456!")
    private String confirmacaoSenha;

}

