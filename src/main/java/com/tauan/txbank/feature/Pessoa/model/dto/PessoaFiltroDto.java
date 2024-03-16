package com.tauan.txbank.feature.Pessoa.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFiltroDto {

    private String nome;

    private Long idadeMax;

    private Long idadeMin;

}
