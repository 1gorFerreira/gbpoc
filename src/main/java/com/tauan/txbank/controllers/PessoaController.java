package com.tauan.txbank.controllers;

import com.tauan.txbank.entities.Pessoa;
import com.tauan.txbank.services.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> buscarTodasPessoas(){
        return pessoaService.buscarTodasPessoas();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPessoaPorId(@PathVariable Long id){
        return pessoaService.buscarPessoaPorId(id);
    }
}
