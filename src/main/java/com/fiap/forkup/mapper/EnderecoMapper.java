package com.fiap.forkup.mapper;

import com.fiap.forkup.domain.dto.EnderecoDTO;
import com.fiap.forkup.domain.entity.Endereco;
import com.fiap.forkup.domain.vo.EnderecoVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoVO vo);

    EnderecoDTO toDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEnderecoFromVO(EnderecoVO vo, @MappingTarget Endereco entity);

}
