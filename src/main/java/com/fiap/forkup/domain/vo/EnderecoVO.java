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
@Schema(description = "Dados de entrada para criar ou atualizar endereço")
public class EnderecoVO {

    public interface Create {}

    public interface Update {}

    @NotNull(groups = {Create.class, Update.class}, message = "Logradouro é obrigatório")
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @NotNull(groups = {Create.class, Update.class}, message = "Número é obrigatório")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apto 45")
    private String complemento;

    @NotNull(groups = {Create.class, Update.class}, message = "Cidade é obrigatória")
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotNull(groups = {Create.class, Update.class}, message = "CEP é obrigatório")
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

    @NotNull(groups = Create.class, message = "ID do usuário é obrigatório")
    @Schema(description = "Identificador do usuário associado ao endereço", example = "1")
    private Long idUsuario;

}
