package com.fiap.forkup.controller.v1;

import com.fiap.forkup.domain.dto.AuthDTO;
import com.fiap.forkup.domain.vo.LoginVO;
import com.fiap.forkup.exception.dto.ApiProblem;
import com.fiap.forkup.exception.handler.BusinessException;
import com.fiap.forkup.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Validação de Login", description = "Endpoint para validação de login")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Valida login do usuario", description = "Recebe login e senha, e valida se estão corretos para autenticar o usuario")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Login ou senha inválidos",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ApiProblem.class)))
    public AuthDTO login(@Valid @RequestBody LoginVO loginVO) throws BusinessException {
        return authService.autenticar(loginVO);
    }

}
