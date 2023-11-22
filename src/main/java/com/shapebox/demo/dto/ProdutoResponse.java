package com.shapebox.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String marca;
    private String categoria;
    private String tamanhoDisponivel;
    private String imagemUrl;
    private int quantidadeEstoque;
}
