package com.fiap.forkup.mapper;

import com.fiap.forkup.domain.dto.UsuarioDTO;
import com.fiap.forkup.domain.entity.Usuario;
import com.fiap.forkup.domain.vo.UsuarioVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioVO vo);

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateUsuarioFromVO(UsuarioVO vo, @MappingTarget Usuario entity);

}

