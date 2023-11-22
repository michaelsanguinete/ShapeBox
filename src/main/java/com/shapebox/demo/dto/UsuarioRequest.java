package com.shapebox.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class UsuarioRequest {

    @NotNull
    private String nome;
    @NotNull
    private String email;
    @CPF
    private String cpf;
    @NotNull
    private String senha;
}
