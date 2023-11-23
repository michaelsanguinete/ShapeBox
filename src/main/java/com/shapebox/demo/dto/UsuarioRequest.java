package com.shapebox.demo.dto;

import com.shapebox.demo.entity.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    @NotNull
    private String nome;
    @NotNull
    private String email;
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotNull
    private String senha;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private String sexo;
    @NotNull
    private String telefone;
    @NotNull
    private EnderecoRequest endereco;
}
