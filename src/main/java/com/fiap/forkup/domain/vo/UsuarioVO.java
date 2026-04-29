package com.fiap.forkup.domain.vo;

import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Dados de entrada para criar ou atualizar usuario")
public class UsuarioVO {

    public interface Create {}

    public interface Update {}

    @NotNull(groups = {Create.class, Update.class}, message = "Nome é obrigatório")
    @Schema(description = "Nome completo do usuario", example = "João Silva")
    private String nome;

    @NotNull(groups = {Create.class, Update.class}, message = "Email é obrigatório")
    @Email(groups = {Create.class, Update.class}, message = "Email deve ser valido")
    @Schema(description = "Email valido do usuario", example = "joao@example.com")
    private String email;

    @NotNull(groups = {Create.class, Update.class}, message = "Login é obrigatório")
    @Schema(description = "Login para autenticacao", example = "joao.silva")
    private String login;

    @NotNull(groups = Create.class, message = "Senha é obrigatória no cadastro")
    @Schema(description = "Senha do usuario (obrigatoria apenas no cadastro)", example = "SenhaSegura123!")
    private String senha;

    @NotNull(groups = {Create.class, Update.class}, message = "Tipo de usuário é obrigatório")
    @Schema(description = "Tipo de usuario (DONO_RESTAURANTE ou CLIENTE)", example = "CLIENTE")
    private TipoUsuarioEnum tipoUsuario;

}
