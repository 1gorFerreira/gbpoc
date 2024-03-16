package com.tauan.txbank.feature.Pessoa.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class PessoaEntradaDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @PositiveOrZero
    private Long idade;

}
