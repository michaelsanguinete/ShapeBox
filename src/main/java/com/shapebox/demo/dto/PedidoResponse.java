package com.shapebox.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shapebox.demo.entity.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoResponse {

    private UsuarioResponse usuario;
    private List<ProdutoResponse> produtos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataPedido;
    private StatusPedido statusPedido;
}
