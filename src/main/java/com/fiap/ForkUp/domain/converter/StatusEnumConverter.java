package com.fiap.ForkUp.domain.converter;

import com.fiap.ForkUp.domain.enumeration.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final StatusEnum statusEnum) {
        return statusEnum != null ? statusEnum.getId() : null;
    }

    @Override
    public StatusEnum convertToEntityAttribute(final Integer id) {
        return StatusEnum.valueOfId(id);
    }
}
