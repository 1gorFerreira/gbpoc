package com.tauan.txbank.feature.Pessoa.model.repositories.specs;

import com.tauan.txbank.feature.Pessoa.model.dto.PessoaFiltroDto;
import com.tauan.txbank.feature.Pessoa.model.entities.Pessoa;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PessoaSpecs {

    public static Specification<Pessoa> usandoFiltro(PessoaFiltroDto pessoaFiltroDto) {
        return (root, query, builder) -> {

            var predicates = new ArrayList<Predicate>();

            if(pessoaFiltroDto.getIdadeMax() != null){
                predicates.add(builder.lessThanOrEqualTo(root.get("idade"), pessoaFiltroDto.getIdadeMax()));
            }

            if(pessoaFiltroDto.getIdadeMin() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("idade"), pessoaFiltroDto.getIdadeMin()));
            }

            if(pessoaFiltroDto.getNome() != null){
                predicates.add(builder.like(root.get("nome"), pessoaFiltroDto.getNome()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
