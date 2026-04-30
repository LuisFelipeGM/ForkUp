package com.fiap.forkup.service;

import com.fiap.forkup.domain.dto.AuthDTO;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.enumeration.ErrorCode;
import com.fiap.forkup.domain.vo.LoginVO;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.exception.handler.UsuarioNaoEncontradoException;
import com.fiap.forkup.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
@Service
public class AuthService {

    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    public AuthDTO autenticar(LoginVO loginVO) throws BusinessException {

        Usuario usuario = usuarioRepository.findByLogin(loginVO.getLogin());

        if (isNull(usuario) || !passwordEncoder.matches(loginVO.getSenha(), usuario.getSenha())) {
            throw new BusinessException("Login ou senha inválidos", HttpStatus.BAD_REQUEST, ErrorCode.AUTH_ERROR);
        }

        return new AuthDTO();

    }

}
