package com.shapebox.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String marca;
    private String categoria;
    private String sku;
    private String tamanhoDisponivel;
    private String imagemUrl;
    private int quantidadeEstoque;
    private String cor;
}
