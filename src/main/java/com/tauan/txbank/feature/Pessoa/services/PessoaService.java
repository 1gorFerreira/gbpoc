package com.tauan.txbank.feature.Pessoa.services;

import com.tauan.txbank.feature.Pessoa.mapper.PessoaMapper;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaSaidaDTO;
import com.tauan.txbank.feature.Pessoa.model.entities.Pessoa;
import com.tauan.txbank.feature.Pessoa.model.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    public List<PessoaSaidaDTO> buscarTodasPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaSaidaDTO> pessoaSaidaDTOS = new ArrayList<>();

        for(Pessoa pessoa : pessoas){
            pessoaSaidaDTOS.add(pessoaMapper.toDTO(pessoa));
        }

        return pessoaSaidaDTOS;
    }

    public Pessoa buscarPessoaPorId(Long id){
        return pessoaRepository.findById(id).orElse(null);
    }

}
