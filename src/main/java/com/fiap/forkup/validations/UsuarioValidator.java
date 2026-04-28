package com.fiap.forkup.validations;

import com.fiap.forkup.domain.vo.UsuarioVO;
import com.fiap.forkup.exception.BusinessException;
import com.fiap.forkup.exception.UsuarioNaoEncontradoException;
import com.fiap.forkup.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
@Component
public class UsuarioValidator {

    private static final Pattern SENHA_FORTE_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$");

    private UsuarioRepository usuarioRepository;

    public void createValidation(final UsuarioVO usuarioVO) throws BusinessException {

        log.info("Validando a criação de usuário!");

        validateEmail(usuarioVO.getEmail(), null);
        validateLogin(usuarioVO.getLogin(), null);
        validatePassword(usuarioVO.getSenha());

        log.info("Validação finalizada na criação de usuário!");
    }

    public void updateValidation(final UsuarioVO usuarioVO, final Long id) throws BusinessException {

        log.info("Iniciando a validação da atualização do usuário com ID: {}", id);

        validateEmail(usuarioVO.getEmail(), id);
        validateLogin(usuarioVO.getLogin(), id);

        log.info("Finalizando a validação da atualização do usuário com ID: {}", id);
    }

    public void deleteValidation(final Long id) throws UsuarioNaoEncontradoException {

        log.info("Iniciando a validação de exclusão do usuário com ID: {}", id);

        validateUsuarioExists(id);

        log.info("Finalizando a validação de exclusão do usuário com ID: {}", id);

    }

    private void validateUsuarioExists(final Long id) throws UsuarioNaoEncontradoException {

        if (BooleanUtils.isFalse(usuarioRepository.existsById(id))) {
            log.warn("Usuário com ID: {} - Não foi encontrado", id);
            throw new UsuarioNaoEncontradoException();
        }

    }

    private void validateEmail(final String email, final Long id) throws BusinessException {

        boolean exists = isNull(id) ? usuarioRepository.existsUsuarioByEmail(email) : usuarioRepository.existsUsuarioByEmailAndIdNot(email, id);

        validateUniqueField(exists, "Email");
    }

    private void validateLogin(final String login, final Long id) throws BusinessException {

        boolean exists = isNull(id) ? usuarioRepository.existsUsuarioByLogin(login) : usuarioRepository.existsUsuarioByLoginAndIdNot(login, id);

        validateUniqueField(exists, "Login");

    }

    private void validatePassword(final String senha) throws BusinessException {

        if (isNull(senha) || !SENHA_FORTE_PATTERN.matcher(senha).matches()) {
            log.warn("Tentativa de cadastro com senha inválida.");
            throw new BusinessException(
                    "Senha invalida. Deve ter no minimo 8 caracteres, letra minuscula, letra maiuscula, numero e 1 caractere especial.",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private void validateUniqueField(final boolean exists, final String fieldName) throws BusinessException {

        if (exists) {
            log.warn("O {} já está cadastrado no sistema.", fieldName);
            throw new BusinessException(fieldName + " já cadastrado", HttpStatus.BAD_REQUEST);
        }
    }


}
