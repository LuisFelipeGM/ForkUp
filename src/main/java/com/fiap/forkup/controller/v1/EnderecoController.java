package com.fiap.forkup.controller.v1;

import com.fiap.forkup.annotation.NotFoundErrorReponse;
import com.fiap.forkup.annotation.ValidationErrorResponse;
import com.fiap.forkup.domain.dto.EnderecoDTO;
import com.fiap.forkup.domain.dto.IdentifierDTO;
import com.fiap.forkup.domain.vo.EnderecoVO;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.handler.EnderecoNaoEncontradoException;
import com.fiap.forkup.exception.handler.UsuarioNaoEncontradoException;
import com.fiap.forkup.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/enderecos")
@Validated
@Tag(name = "Endereços", description = "Endpoints para gerenciamento de endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista endereços de um usuário", description = "Retorna uma lista de endereços associados a um usuário específico")
    @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso")
    public List<EnderecoDTO> findAllEnderecoUsuario(@Parameter(description = "Identificador do Usuário", example = "1") @PathVariable final Long idUsuario) throws UsuarioNaoEncontradoException {
        return enderecoService.findAllEnderecoUsuario(idUsuario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca endereço por id", description = "Retorna os dados de um endereço especifico")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado")
    @NotFoundErrorReponse
    public EnderecoDTO findById(@Parameter(description = "Identificador do Endereço", example = "1") @PathVariable final Long id) throws BusinessException {
        return enderecoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo endereço", description = "Cria um novo endereço com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso", content = @Content(schema = @Schema(implementation = IdentifierDTO.class)))
    @ValidationErrorResponse
    public IdentifierDTO create(@RequestBody @Validated(EnderecoVO.Create.class) EnderecoVO enderecoVO) throws BusinessException {
        return enderecoService.create(enderecoVO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza dados de um endereço", description = "Atualiza os dados de um endereço existente")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ValidationErrorResponse
    @NotFoundErrorReponse
    public EnderecoDTO update(@Parameter(description = "Identificador do Endereço", example = "1") @PathVariable Long id,
             @RequestBody @Validated(EnderecoVO.Update.class) EnderecoVO enderecoVO) throws BusinessException {
        return enderecoService.update(id, enderecoVO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove o endereço por id", description = "Deleta um endereço existente")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @NotFoundErrorReponse
    public void delete(@Parameter(description = "Identificador do Endereço", example = "1") @PathVariable Long id) throws EnderecoNaoEncontradoException {
        enderecoService.delete(id);
    }

}
