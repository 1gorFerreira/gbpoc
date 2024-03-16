package com.tauan.txbank.services;

import com.tauan.txbank.entities.Pessoa;
import com.tauan.txbank.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodasPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long id){
        return pessoaRepository.findById(id).orElse(null);
    }

}
