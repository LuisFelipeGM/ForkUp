package com.fiap.forkup.domain.entity;

import com.fiap.forkup.constants.WhereStatusConstants;
import com.fiap.forkup.domain.converter.StatusEnumConverter;
import com.fiap.forkup.domain.converter.TipoUsuarioConverter;
import com.fiap.forkup.domain.enumeration.StatusEnum;
import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@SQLRestriction(WhereStatusConstants.NOT_EQUALS_EXCLUIDO)
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

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;

    @PrePersist
    private void statusAtivo() {
        this.status = StatusEnum.ATIVO;
    }

    @PreUpdate
    @PreRemove
    private void dataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
