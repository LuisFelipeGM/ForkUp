package com.fiap.forkup.domain.enumeration;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TipoUsuarioEnum {

    DONO_RESTAURANTE(1, "Dono de Restaurante", 2),
    CLIENTE(2, "Cliente", 1);

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
    private Integer limiteEnderecos;

    TipoUsuarioEnum(final Integer id, final String descricao, final Integer limiteEnderecos) {
        this.id = id;
        this.descricao = descricao;
        this.limiteEnderecos = limiteEnderecos;
    }
}
