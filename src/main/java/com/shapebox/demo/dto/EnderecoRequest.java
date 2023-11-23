package com.shapebox.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {

    private String cep;
    private int numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}
