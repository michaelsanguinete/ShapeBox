package com.shapebox.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(length = 16777215)
    private byte[] conteudo;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
