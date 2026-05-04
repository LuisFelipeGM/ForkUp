package com.fiap.forkup.validations;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import com.fiap.forkup.domain.vo.EnderecoVO;
import com.fiap.forkup.exception.dto.FieldErrorDetail;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.handler.EnderecoNaoEncontradoException;
import com.fiap.forkup.repository.EnderecoRepository;
import com.fiap.forkup.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class EnderecoValidator extends Validator {

    private final EnderecoRepository enderecoRepository;

    private final UsuarioRepository usuarioRepository;

    public void createValidation(final EnderecoVO enderecoVO) throws BusinessException {
        log.info("Validando a criação de endereço para o usuário com ID: {}", enderecoVO.getIdUsuario());

        List<FieldErrorDetail> errors = new ArrayList<>();

        validateQuantidadeEnderecos(enderecoVO.getIdUsuario(), errors);
        validateCEP(enderecoVO.getCep(), errors);

        existErrors(errors);

        log.info("Validação finalizada na criação de endereço para o usuário com ID: {}", enderecoVO.getIdUsuario());
    }

    public void updateValidation(final EnderecoVO enderecoVO, final Long id) throws BusinessException {

        log.info("Validando a atualização do endereço com ID: {}", id);

        List<FieldErrorDetail> errors = new ArrayList<>();

        validateCEP(enderecoVO.getCep(), errors);

        existErrors(errors);

        log.info("Validação finalizada na atualização de endereço com ID: {}", id);

    }

    public void deleteValidation(final Long id) throws EnderecoNaoEncontradoException {

        log.info("Iniciando a validação de exclusão do endereço com ID: {}", id);

        validateEnderecoExists(id);

        log.info("Finalizando a validação de exclusão do endereço com ID: {}", id);

    }

    public void validateEnderecoExists(final Long id) throws EnderecoNaoEncontradoException {

        if (BooleanUtils.isFalse(enderecoRepository.existsById(id))) {
            log.warn("Endereço com ID: {} - Não foi encontrado", id);
            throw new EnderecoNaoEncontradoException();
        }

    }

    private void validateQuantidadeEnderecos(final Long idUsuario, List<FieldErrorDetail> errors) {

        Long quantidadeAtual = enderecoRepository.countEnderecosByUsuarioId(idUsuario);
        TipoUsuarioEnum tipoUsuario = usuarioRepository.findTipoUsuarioById(idUsuario);

        if (quantidadeAtual >= tipoUsuario.getLimiteEnderecos()) {
            log.warn("Usuário com o ID: {} - Já cadastrou o limite de endereços", idUsuario);
            errors.add(new FieldErrorDetail(tipoUsuario.getDescricao(), "Limite de endereços atingido para o tipo de usuário"));
        }

    }

    private void validateCEP(final String cep, List<FieldErrorDetail> errors) {

        String cepLimpo = cep.replace("-", "").trim();

        if (!cepLimpo.matches("\\d+")) {
            log.warn("CEP contém caracteres inválidos: {}", cep);
            errors.add(new FieldErrorDetail("cep", "CEP deve conter apenas dígitos"));
            return;
        }

        if (cepLimpo.length() != 8) {
            log.warn("CEP com comprimento inválido: {}. Esperado 8 dígitos, recebido: {}", cep, cepLimpo.length());
            errors.add(new FieldErrorDetail("cep", "CEP deve conter exatamente 8 dígitos"));
        }

    }

}
