package com.fiap.forkup.repository;

import com.fiap.forkup.domain.dto.EnderecoDTO;
import com.fiap.forkup.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT NEW com.fiap.forkup.domain.dto.EnderecoDTO(" +
            "e.id, e.logradouro, e.numero, e.complemento, e.cidade, e.cep, e.dataCriacao, e.status) " +
            "FROM Endereco e " +
            "WHERE e.usuario.id = :idUsuario")
    List<EnderecoDTO> findAllEnderecosByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT NEW com.fiap.forkup.domain.dto.EnderecoDTO(" +
            "e.id, e.logradouro, e.numero, e.complemento, e.cidade, e.cep, e.dataCriacao, e.status) " +
            "FROM Endereco e " +
            "WHERE e.id = :idEndereco")
    Optional<EnderecoDTO> findEnderecosById(@Param("idEndereco") Long idEndereco);

    @Transactional
    @Modifying
    @Query("UPDATE Endereco e SET e.status = com.fiap.forkup.domain.enumeration.StatusEnum.EXCLUIDO " +
            "WHERE e.id = :idEndereco")
    void excluirEndereco(@Param("idEndereco") Long idEndereco);

    @Query("SELECT COUNT(e) FROM Usuario u JOIN u.enderecos e WHERE u.id = :idUsuario")
    Long countEnderecosByUsuarioId(@Param("idUsuario") Long idUsuario);

}
