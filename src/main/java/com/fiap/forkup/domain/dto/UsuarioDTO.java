package com.fiap.forkup.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.forkup.domain.enumeration.StatusEnum;
import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
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
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String login;

    private String enderecoCompleto;

    private TipoUsuarioEnum tipoUsuario;

    private StatusEnum status;

    private LocalDateTime dataCriacao;

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
