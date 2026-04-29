package com.fiap.forkup.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.forkup.domain.enumeration.StatusEnum;
import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(description = "DTO de resposta com dados do usuario")
public class UsuarioDTO {

    @Schema(description = "Identificador unico do usuario", example = "1")
    private Long id;

    @Schema(description = "Nome completo do usuario", example = "João Silva")
    private String nome;

    @Schema(description = "Email do usuario", example = "joao@example.com")
    private String email;

    @Schema(description = "Login para autenticacao", example = "joao.silva")
    private String login;

    @Schema(description = "Tipo de usuario (DONO_RESTAURANTE ou CLIENTE)", example = "CLIENTE")
    private TipoUsuarioEnum tipoUsuario;

    @Schema(description = "Status do usuario", example = "ATIVO")
    private StatusEnum status;

    @Schema(description = "Data de criacao do registro")
    private LocalDateTime dataCriacao;

    @Schema(description = "Data da ultima atualizacao")
    private LocalDateTime dataAtualizacao;

    public UsuarioDTO(Long id, String nome, String email, String login, TipoUsuarioEnum tipoUsuario, StatusEnum status,
                      LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}
