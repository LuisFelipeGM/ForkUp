package com.fiap.forkup.repository;

import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT NEW com.fiap.forkup.domain.dto.UsuarioDTO(" +
            "u.id, u.nome, u.email, u.login, u.tipoUsuario, u.status, u.dataCriacao, u.dataAtualizacao) " +
            "FROM Usuario u " +
            "WHERE (:nome IS NULL OR :nome = '' OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) ORDER BY u.id ASC ",
            countQuery = "SELECT COUNT(u.id) FROM Usuario u " +
                    "WHERE (:nome IS NULL OR :nome = '' OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
    Page<UsuarioDTO> findAllPaginado(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT NEW com.fiap.forkup.domain.dto.UsuarioDTO(" +
            "u.id, u.nome, u.email, u.login, u.tipoUsuario, u.status, u.dataCriacao, u.dataAtualizacao) " +
            "FROM Usuario u " +
            "WHERE u.id = :idUsuario")
    Optional<UsuarioDTO> findByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.status = com.fiap.forkup.domain.enumeration.StatusEnum.EXCLUIDO, " +
            "u.dataAtualizacao = :dataAtualizacao " +
            "WHERE u.id = :idUsuario")
    void excluirUsuario(@Param("idUsuario") Long idUsuario, @Param("dataAtualizacao") LocalDateTime dataAtualizacao);

    Usuario findByLogin(String login);

    boolean existsUsuarioByEmail(String email);

    boolean existsUsuarioByEmailAndIdNot(String email, Long id);

    boolean existsUsuarioByLogin(String login);

    boolean existsUsuarioByLoginAndIdNot(String login, Long id);

    @Query("SELECT u.tipoUsuario FROM Usuario u WHERE u.id = :id")
    TipoUsuarioEnum findTipoUsuarioById(Long id);

}
