package com.fiap.ForkUp.domains.entity;

import com.fiap.ForkUp.domains.converter.StatusEnumConverter;
import com.fiap.ForkUp.domains.converter.TipoUsuarioConverter;
import com.fiap.ForkUp.domains.enumeration.StatusEnum;
import com.fiap.ForkUp.domains.enumeration.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "tipo_usuario_id")
    @Convert(converter = TipoUsuarioConverter.class)
    private TipoUsuarioEnum tipoUsuario;

    @Column(name = "status_id")
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status;

    @PreUpdate
    @PreRemove
    private void dataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
