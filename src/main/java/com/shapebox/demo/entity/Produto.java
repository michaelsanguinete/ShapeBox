package com.shapebox.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String marca;
    private String categoria;
    private String tamanhoDisponivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<Imagem> imagens;
    private int quantidadeEstoque;
    @Column(unique = true)
    private String sku;
    private String cor;
    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;
}
