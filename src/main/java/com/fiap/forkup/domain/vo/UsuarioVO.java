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

    public interface Create {}

    public interface Update {}

    @NotNull(groups = {Create.class, Update.class})
    private String nome;

    @NotNull(groups = {Create.class, Update.class})
    @Email(groups = {Create.class, Update.class})
    private String email;

    @NotNull(groups = {Create.class, Update.class})
    private String login;

    @NotNull(groups = Create.class, message = "Senha é obrigatória no cadastro")
    private String senha;

    @NotNull(groups = {Create.class, Update.class})
    private TipoUsuarioEnum tipoUsuario;

}
