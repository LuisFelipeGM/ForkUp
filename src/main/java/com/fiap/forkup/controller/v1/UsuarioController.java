package com.fiap.forkup.controller.v1;

import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.UsuarioNaoEncontradoException;
import com.fiap.forkup.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioDTO> listaPaginada(String nome, Pageable pageable) {
        return usuarioService.listaPaginada(nome, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscarPorId(@PathVariable Long id) throws UsuarioNaoEncontradoException {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO cadastrar(@RequestBody UsuarioVO usuarioVO) {
        return null;
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody UsuarioVO usuarioVO) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

    }
}
