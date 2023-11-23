package com.shapebox.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String telefone;

    @NotNull @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @CPF(message = "CPF inválido") @Column(unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}
