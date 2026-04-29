package com.fiap.forkup.controller.v1;

import com.fiap.forkup.domain.dto.IdentifierDTO;
import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.vo.AlterarSenhaVO;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.annotation.NotFoundErrorReponse;
import com.fiap.forkup.exception.annotation.ValidationErrorResponse;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.handler.UsuarioNaoEncontradoException;
import com.fiap.forkup.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista usuarios de forma paginada", description = "Retorna uma lista paginada de usuarios com filtro opcional por nome")
    @ApiResponse(responseCode = "200", description = "Lista paginada retornada com sucesso")
    public Page<UsuarioDTO> listaPaginada(@Parameter(description = "Filtro opcional por nome") @RequestParam(value = "nome", required = false) String nome,
            @Parameter(description = "Numero da pagina (inicia em 0)", example = "0") @RequestParam(value = "page", defaultValue = "0") Integer page,
            @Parameter(description = "Quantidade de itens por pagina", example = "10") @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioService.listaPaginada(nome, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca usuario por id", description = "Retorna os dados de um usuario especifico")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @NotFoundErrorReponse
    public UsuarioDTO buscarPorId(@Parameter(description = "Identificador do usuario", example = "1") @PathVariable Long id) throws UsuarioNaoEncontradoException {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo usuario", description = "Cria um novo usuario com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(schema = @Schema(implementation = IdentifierDTO.class)))
    @ValidationErrorResponse
    public IdentifierDTO cadastrar(@Validated(UsuarioVO.Create.class) @RequestBody UsuarioVO usuarioVO) throws BusinessException {
        return usuarioService.create(usuarioVO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza dados de um usuario", description = "Atualiza os dados de um usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ValidationErrorResponse
    @NotFoundErrorReponse
    public UsuarioDTO atualizar(@Parameter(description = "Identificador do usuario", example = "1") @PathVariable Long id,
            @RequestBody @Validated(UsuarioVO.Update.class) UsuarioVO usuarioVO) throws BusinessException {
        return usuarioService.update(id, usuarioVO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove usuario por id", description = "Deleta um usuario existente")
    @ApiResponse(responseCode = "204", description = "Usuario removido com sucesso")
    @NotFoundErrorReponse
    public void deletar(@Parameter(description = "Identificador do usuario", example = "1") @PathVariable Long id) throws UsuarioNaoEncontradoException {
        usuarioService.delete(id);
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Troca a senha do usuario", description = "Altera a senha de um usuario existente")
    @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso")
    @ValidationErrorResponse
    @NotFoundErrorReponse
    public void trocarSenha(@Parameter(description = "Identificador do usuario", example = "1") @PathVariable Long id,
            @RequestBody @Valid AlterarSenhaVO alterarSenhaVO) throws BusinessException {
        usuarioService.alterarSenha(id, alterarSenhaVO);
    }
}
