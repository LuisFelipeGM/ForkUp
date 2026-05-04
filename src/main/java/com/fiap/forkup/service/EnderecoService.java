package com.fiap.forkup.service;

import com.fiap.forkup.domain.dto.EnderecoDTO;
import com.fiap.forkup.domain.dto.IdentifierDTO;
import com.fiap.forkup.domain.entity.Endereco;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.vo.EnderecoVO;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.handler.EnderecoNaoEncontradoException;
import com.fiap.forkup.exception.handler.UsuarioNaoEncontradoException;
import com.fiap.forkup.mapper.EnderecoMapper;
import com.fiap.forkup.repository.EnderecoRepository;
import com.fiap.forkup.validations.EnderecoValidator;
import com.fiap.forkup.validations.UsuarioValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    private EnderecoValidator enderecoValidator;

    private UsuarioValidator usuarioValidator;

    private EnderecoMapper enderecoMapper;

    public List<EnderecoDTO> findAllEnderecoUsuario(final Long idUsuario) throws UsuarioNaoEncontradoException {

        log.info("Buscando endereços do usuário com ID: {}", idUsuario);

        usuarioValidator.validateUsuarioExists(idUsuario);

        List<EnderecoDTO> enderecos = enderecoRepository.findAllEnderecosByIdUsuario(idUsuario);

        log.info("** SAYONARA ** - Endereços encontrados para o usuário com ID {}: {}", idUsuario, enderecos.size());

        return enderecos;

    }

    public EnderecoDTO findById(final Long id) throws BusinessException {
        return enderecoRepository.findEnderecosById(id)
                .orElseThrow(EnderecoNaoEncontradoException::new);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public IdentifierDTO create(final EnderecoVO enderecoVO) throws BusinessException {

        log.info("Iniciando a criação do endereço para o usuário com ID: {}", enderecoVO.getIdUsuario());

        usuarioValidator.validateUsuarioExists(enderecoVO.getIdUsuario());
        enderecoValidator.createValidation(enderecoVO);

        Endereco endereco = enderecoMapper.toEntity(enderecoVO);
        endereco.setUsuario(Usuario.builder().id(enderecoVO.getIdUsuario()).build());

        endereco = enderecoRepository.save(endereco);

        log.info("** SAYONARA ** - Endereço Criado com Sucesso com ID {} - para o usuário com ID: {}", endereco.getId(), enderecoVO.getIdUsuario());

        return new IdentifierDTO(endereco.getId());
    }

    @Transactional(rollbackFor = BusinessException.class)
    public EnderecoDTO update(final Long id, final EnderecoVO enderecoVO) throws BusinessException {

        log.info("Iniciando a atualização do endereço com ID: {}", id);

        enderecoVO.setIdUsuario(null);

        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(EnderecoNaoEncontradoException::new);

        enderecoValidator.updateValidation(enderecoVO, id);

        enderecoMapper.updateEnderecoFromVO(enderecoVO, endereco);
        enderecoRepository.save(endereco);

        log.info("** SAYONARA ** - Atualização de endereço com o ID: {} - Realizado com sucesso.", id);

        return enderecoMapper.toDTO(endereco);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void delete(final Long id) throws EnderecoNaoEncontradoException {

        log.info("Iniciando a exclusão do endereço com ID: {}", id);

        enderecoValidator.deleteValidation(id);

        enderecoRepository.excluirEndereco(id);

        log.info("** SAYONARA ** - Exclusão de endereço com o ID: {} - Realizado com sucesso.", id);

    }

}
