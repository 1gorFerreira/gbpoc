package com.tauan.txbank.feature.Pessoa.controllers;

import com.tauan.txbank.feature.Pessoa.model.dto.PessoaEntradaDTO;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaFiltroDto;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaSaidaDTO;
import com.tauan.txbank.feature.Pessoa.model.entities.Pessoa;
import com.tauan.txbank.feature.Pessoa.services.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public Page<PessoaSaidaDTO> buscarTodasPessoas(Pageable pageable, PessoaFiltroDto pessoaFiltroDto){
        return pessoaService.buscarTodasPessoas(pageable, pessoaFiltroDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaSaidaDTO> buscarPessoaPorId(@PathVariable Long id){
        PessoaSaidaDTO saidaDto = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(saidaDto);
    }

    @PostMapping
    public ResponseEntity<PessoaSaidaDTO> salvarPessoa(@RequestBody @Valid PessoaEntradaDTO pessoaEntradaDTO){
        PessoaSaidaDTO pessoaSaidaDTO = pessoaService.salvarPessoa(pessoaEntradaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoaSaidaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaSaidaDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PessoaSaidaDTO> atualizarPessoa(@RequestBody @Valid PessoaEntradaDTO pessoaEntradaDTO, @PathVariable Long id){
        PessoaSaidaDTO pessoaSaidaDTO = pessoaService.atualizarPessoa(pessoaEntradaDTO, id);
        return ResponseEntity.ok(pessoaSaidaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id){
        pessoaService.removerPessoa(id);
        return ResponseEntity.noContent().build();
    }

}
