package com.fiap.forkup.repository;

import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT NEW com.fiap.forkup.domain.dto.UsuarioDTO(" +
            "u.id, u.nome, u.email, u.login, null, u.tipoUsuario, u.status, u.dataCriacao, u.dataAtualizacao) " +
            "FROM Usuario u " +
            "WHERE (:nome IS NULL OR :nome = '' OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))",
            countQuery = "SELECT COUNT(u.id) FROM Usuario u " +
                    "WHERE (:nome IS NULL OR :nome = '' OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
    Page<UsuarioDTO> findAllPaginado(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT NEW com.fiap.forkup.domain.dto.UsuarioDTO(" +
            "u.id, u.nome, u.email, u.login, null, u.tipoUsuario, u.status, u.dataCriacao, u.dataAtualizacao) " +
            "FROM Usuario u " +
            "WHERE u.id = :idUsuario")
    Optional<UsuarioDTO> findByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.status = com.fiap.forkup.domain.enumeration.StatusEnum.EXCLUIDO, " +
            "u.dataAtualizacao = CURRENT_TIMESTAMP " +
            "WHERE u.id = :idUsuario")
    void excluirUsuario(@Param("idUsuario") Long idUsuario);

}
