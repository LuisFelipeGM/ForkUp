package com.fiap.forkup.domain.vo;

import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(groups = {Create.class, Update.class}, min = 2, max = 255, message = "O Nome deve conter entre 2 e 255 caracteres")
    @Schema(description = "Nome completo do usuario", example = "João Silva")
    private String nome;

    @NotNull(groups = {Create.class, Update.class}, message = "Email é obrigatório")
    @Email(groups = {Create.class, Update.class}, message = "Email deve ser valido")
    @Size(groups = {Create.class, Update.class}, min = 10, max = 255, message = "O Email deve conter entre 10 e 255 caracteres")
    @Schema(description = "Email valido do usuario", example = "joao@example.com")
    private String email;

    @NotNull(groups = {Create.class, Update.class}, message = "Login é obrigatório")
    @Size(groups = {Create.class, Update.class}, min = 2, max = 255, message = "O Login deve conter entre 2 e 255 caracteres")
    @Schema(description = "Login para autenticacao", example = "joao.silva")
    private String login;

    @NotNull(groups = Create.class, message = "Senha é obrigatória no cadastro")
    @Size(groups = Create.class, min = 8, max = 255, message = "A Senha deve conter entre 8 e 255 caracteres")
    @Schema(description = "Senha do usuario (obrigatoria apenas no cadastro)", example = "SenhaSegura123!")
    private String senha;

    @NotNull(groups = {Create.class, Update.class}, message = "Tipo de usuário é obrigatório")
    @Schema(description = "Tipo de usuario (DONO_RESTAURANTE ou CLIENTE)", example = "CLIENTE")
    private TipoUsuarioEnum tipoUsuario;

}
