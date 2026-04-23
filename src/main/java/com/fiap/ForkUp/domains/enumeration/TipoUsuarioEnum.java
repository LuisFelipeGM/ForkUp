package com.fiap.ForkUp.domains.enumeration;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TipoUsuarioEnum {

    DONO_RESTAURANTE(1, "Dono de Restaurante"),
    CLIENTE(2, "Cliente");

    private static final Map<Integer, TipoUsuarioEnum> LOOKUP = new HashMap<>();

    static {
        for (TipoUsuarioEnum e : TipoUsuarioEnum.values()) {
            LOOKUP.put(e.getId(), e);
        }
    }

    public static TipoUsuarioEnum valueOfId(Integer id) {
        return LOOKUP.get(id);
    }

    private Integer id;
    private String descricao;

    TipoUsuarioEnum(final Integer id, final String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
