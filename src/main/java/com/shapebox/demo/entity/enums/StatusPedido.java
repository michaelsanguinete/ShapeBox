package com.shapebox.demo.entity.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {

    REALIZADO,
    EM_PROVACAO,
    ENVIADO,
    CANCELADO,
    DEVOLVIDO,
    TROCA_SOLICITADA,
    TROCA_APROVADA,
    TROCA_REJEITADA,
    FINALIZADO;
}
