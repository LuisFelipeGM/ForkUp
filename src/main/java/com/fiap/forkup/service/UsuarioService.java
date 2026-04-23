package com.fiap.forkup.service;

import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.UsuarioNaoEncontradoException;
import com.fiap.forkup.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> listaPaginada(String nome, Pageable pageable) {
        return usuarioRepository.findAllPaginado(nome, pageable);
    }

    public UsuarioDTO buscarPorId(final Long id) throws UsuarioNaoEncontradoException {
        return usuarioRepository.findByIdUsuario(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public UsuarioDTO cadastrar(final UsuarioVO usuarioVO) {
        return null;
    }

    public UsuarioDTO update(final UsuarioVO usuarioVO) {
        return null;
    }

    public void delete(final Long id) {
        // VALIDATE USUARIO
        usuarioRepository.excluirUsuario(id);
    }

}
