package com.fiap.ForkUp.domains.converter;

import com.fiap.ForkUp.domains.enumeration.StatusEnum;
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
