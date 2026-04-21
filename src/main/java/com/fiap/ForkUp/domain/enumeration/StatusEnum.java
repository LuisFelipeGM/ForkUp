package com.fiap.ForkUp.domain.enumeration;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum StatusEnum {

    ATIVO(1, "Ativo"),
    EXCLUIDO(2, "Excluido");

    private static final Map<Integer, StatusEnum> LOOKUP = new HashMap<>();

    static {
        for (StatusEnum e : StatusEnum.values()) {
            LOOKUP.put(e.getId(), e);
        }
    }

    public static StatusEnum valueOfId(Integer id) {
        return LOOKUP.get(id);
    }

    private Integer id;
    private String descricao;

    StatusEnum(final Integer id, final String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
