package com.fiap.forkup.domain.converter;

import com.fiap.forkup.domain.enumeration.TipoUsuarioEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuarioEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final TipoUsuarioEnum tipoUsuarioEnum) {
        return tipoUsuarioEnum != null ? tipoUsuarioEnum.getId() : null;
    }

    @Override
    public TipoUsuarioEnum convertToEntityAttribute(final Integer id) {
        return TipoUsuarioEnum.valueOfId(id);
    }
}
