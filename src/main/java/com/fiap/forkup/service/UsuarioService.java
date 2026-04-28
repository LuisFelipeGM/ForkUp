package com.fiap.forkup.service;

import com.fiap.forkup.domain.dto.IdentifierDTO;
import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.BusinessException;
import com.fiap.forkup.exception.UsuarioNaoEncontradoException;
import com.fiap.forkup.mapper.UsuarioMapper;
import com.fiap.forkup.repository.UsuarioRepository;
import com.fiap.forkup.validations.UsuarioValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private UsuarioValidator usuarioValidator;

    private UsuarioMapper usuarioMapper;


    public Page<UsuarioDTO> listaPaginada(String nome, Pageable pageable) {
        return usuarioRepository.findAllPaginado(nome, pageable);
    }

    public UsuarioDTO buscarPorId(final Long id) throws UsuarioNaoEncontradoException {
        return usuarioRepository.findByIdUsuario(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public IdentifierDTO create(final UsuarioVO usuarioVO) throws BusinessException {

        log.info("Iniciando a criação do usuário com login: {}", usuarioVO.getLogin());

        usuarioValidator.createValidation(usuarioVO);

        Usuario usuario = usuarioMapper.toEntity(usuarioVO);
        usuario.setSenha(usuarioVO.getSenha());

        usuario = usuarioRepository.save(usuario);

        log.info("** SAYONARA ** - Usuário Criado com Sucesso com ID {}", usuario.getId());

        return new IdentifierDTO(usuario.getId());
    }

    @Transactional(rollbackFor = BusinessException.class)
    public UsuarioDTO update(final Long id, UsuarioVO usuarioVO) throws BusinessException {

        log.info("Iniciando a atualização do usuário com ID: {}", id);

        usuarioVO.setSenha(null);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        usuarioValidator.updateValidation(usuarioVO, id);

        usuarioMapper.updateUsuarioFromVO(usuarioVO, usuario);
        usuario = usuarioRepository.save(usuario);

        log.info("Atualização de usuário com o ID: {} - Realizado com sucesso.", id);

        return usuarioMapper.toDTO(usuario);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void delete(final Long id) throws UsuarioNaoEncontradoException {

        log.info("Iniciando a exclusão do usuário com ID: {}", id);

        usuarioValidator.deleteValidation(id);

        usuarioRepository.excluirUsuario(id, LocalDateTime.now());

        log.info("Exclusão do usuário com ID: {} - Realizado com sucesso.", id);
    }

}
