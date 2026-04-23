package com.fiap.forkup.domain.vo;

import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import jakarta.validation.constraints.Email;
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
public class UsuarioVO {

    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String login;

    private String senha;

    @NotNull
    private TipoUsuarioEnum tipoUsuario;

}
