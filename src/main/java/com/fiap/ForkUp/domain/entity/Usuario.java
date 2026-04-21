package com.fiap.ForkUp.domain.entity;

import com.fiap.ForkUp.domain.converter.StatusEnumConverter;
import com.fiap.ForkUp.domain.converter.TipoUsuarioConverter;
import com.fiap.ForkUp.domain.enumeration.StatusEnum;
import com.fiap.ForkUp.domain.enumeration.TipoUsuarioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
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
