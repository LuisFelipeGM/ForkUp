package com.fiap.forkup.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.forkup.domain.enumeration.StatusEnum;
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
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO de resposta com dados do endereco")
public class EnderecoDTO {

    @Schema(description = "Identificador unico do endereço", example = "1")
    private Long id;

    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apto 45")
    private String complemento;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

    @Schema(description = "Endereço completo formatado", example = "Rua das Flores, 123, Apto 45, São Paulo, 12345-678")
    private String enderecoCompleto;

    @Schema(description = "Status do usuario", example = "ATIVO")
    private StatusEnum status;

    @Schema(description = "Data de criacao do registro")
    private LocalDateTime dataCriacao;

    public EnderecoDTO(Long id, String logradouro, String numero, String complemento, String cidade, String cep, LocalDateTime dataCriacao, StatusEnum status) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
        this.enderecoCompleto = String.format("%s, %s, %s, %s - %s", logradouro, numero, complemento, cidade, cep);
        this.dataCriacao = dataCriacao;
        this.status = status;
    }
}
