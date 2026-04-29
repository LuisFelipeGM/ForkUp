package com.fiap.forkup.validations;

import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.vo.AlterarSenhaVO;
import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.dto.FieldErrorDetail;
import com.fiap.forkup.exception.handler.UsuarioNaoEncontradoException;
import com.fiap.forkup.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
@Component
public class UsuarioValidator {

    private static final Pattern SENHA_FORTE_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$");

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public void createValidation(final UsuarioVO usuarioVO) throws BusinessException {

        log.info("Validando a criação de usuário!");

        List<FieldErrorDetail> errors = new ArrayList<>();

        validateEmail(usuarioVO.getEmail(), null, errors);
        validateLogin(usuarioVO.getLogin(), null, errors);
        validatePassword(usuarioVO.getSenha(), errors);

        existErrors(errors);

        log.info("Validação finalizada na criação de usuário!");
    }

    public void updateValidation(final UsuarioVO usuarioVO, final Long id) throws BusinessException {

        log.info("Iniciando a validação da atualização do usuário com ID: {}", id);

        List<FieldErrorDetail> errors = new ArrayList<>();

        validateEmail(usuarioVO.getEmail(), id, errors);
        validateLogin(usuarioVO.getLogin(), id, errors);

        existErrors(errors);

        log.info("Finalizando a validação da atualização do usuário com ID: {}", id);
    }

    public void deleteValidation(final Long id) throws UsuarioNaoEncontradoException {

        log.info("Iniciando a validação de exclusão do usuário com ID: {}", id);

        validateUsuarioExists(id);

        log.info("Finalizando a validação de exclusão do usuário com ID: {}", id);

    }

    public void changePasswordValidation(final Usuario usuario, final AlterarSenhaVO vo) throws BusinessException {

        log.info("Iniciando a validação da troca de senha do usuário com ID: {}", usuario.getId());

        List<FieldErrorDetail> errors = new ArrayList<>();

        validateCurrentPassword(vo.getSenhaAtual(), usuario.getSenha(), errors);
        validatePassword(vo.getNovaSenha(), errors);
        validateNewPasswordIsDifferent(vo.getNovaSenha(), usuario.getSenha(), errors);
        validatePasswordConfirmation(vo.getNovaSenha(), vo.getConfirmacaoSenha(), errors);

        existErrors(errors);

        log.info("Finalizando a validação da troca de senha do usuário com ID: {}", usuario.getId());
    }

    private void validateUsuarioExists(final Long id) throws UsuarioNaoEncontradoException {

        if (BooleanUtils.isFalse(usuarioRepository.existsById(id))) {
            log.warn("Usuário com ID: {} - Não foi encontrado", id);
            throw new UsuarioNaoEncontradoException();
        }

    }

    private void validateEmail(final String email, final Long id, List<FieldErrorDetail> errors) throws BusinessException {

        boolean exists = isNull(id) ? usuarioRepository.existsUsuarioByEmail(email) : usuarioRepository.existsUsuarioByEmailAndIdNot(email, id);

        validateUniqueField(exists, "Email", errors);
    }

    private void validateLogin(final String login, final Long id, List<FieldErrorDetail> errors) throws BusinessException {

        boolean exists = isNull(id) ? usuarioRepository.existsUsuarioByLogin(login) : usuarioRepository.existsUsuarioByLoginAndIdNot(login, id);

        validateUniqueField(exists, "Login", errors);

    }

    private void validatePassword(final String senha, List<FieldErrorDetail> errors) {

        if (isNull(senha) || !SENHA_FORTE_PATTERN.matcher(senha).matches()) {
            log.warn("Tentativa de cadastro com senha inválida.");
            errors.add(new FieldErrorDetail(
                    "senha",
                    "Senha deve ter no mínimo 8 caracteres, incluindo maiúscula, minúscula, número e especial"
            ));
        }
    }

    private void validateUniqueField(final boolean exists, final String fieldName, List<FieldErrorDetail> errors) {

        if (exists) {
            log.warn("O {} já está cadastrado no sistema.", fieldName);
            errors.add(new FieldErrorDetail(fieldName, fieldName + " já cadastrado"));
        }

    }

    private void validateCurrentPassword(final String senhaAtual, final String senhaArmazenada, List<FieldErrorDetail> errors) {

        if (!passwordEncoder.matches(senhaAtual, senhaArmazenada)) {
            errors.add(new FieldErrorDetail("senhaAtual", "Senha atual inválida"));
        }

    }

    private void validateNewPasswordIsDifferent(final String novaSenha, final String senhaArmazenada, List<FieldErrorDetail> errors) {

        if (passwordEncoder.matches(novaSenha, senhaArmazenada)) {
            errors.add(new FieldErrorDetail("novaSenha", "A nova senha deve ser diferente da atual"));
        }

    }

    private void validatePasswordConfirmation(final String novaSenha, final String confirmacaoSenha, List<FieldErrorDetail> errors) {

        if (!Objects.equals(novaSenha, confirmacaoSenha)) {
            errors.add(new FieldErrorDetail("confirmacaoSenha", "Confirmação de senha não confere"));
        }

    }

    private void existErrors(List<FieldErrorDetail> errors) throws BusinessException {

        if (!errors.isEmpty()) {
            throw new BusinessException("Erro de validação", HttpStatus.BAD_REQUEST, ErrorCode.VALIDATION_ERROR).addMetadata("fields", errors);
        }

    }


}
