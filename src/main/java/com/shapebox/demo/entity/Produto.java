package com.shapebox.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
