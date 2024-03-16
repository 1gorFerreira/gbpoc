package com.tauan.txbank.feature.Pessoa.services;

import com.tauan.txbank.core.handle.exceptions.RecursoNaoEncontradoException;
import com.tauan.txbank.feature.Pessoa.mapper.PessoaMapper;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaEntradaDTO;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaFiltroDto;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaSaidaDTO;
import com.tauan.txbank.feature.Pessoa.model.entities.Pessoa;
import com.tauan.txbank.feature.Pessoa.model.repositories.PessoaRepository;
import com.tauan.txbank.feature.Pessoa.model.repositories.specs.PessoaSpecs;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    public Page<PessoaSaidaDTO> buscarTodasPessoas(Pageable pageable, PessoaFiltroDto pessoaFiltroDto){
        Page<Pessoa> pessoas = pessoaRepository.findAll(PessoaSpecs.usandoFiltro(pessoaFiltroDto), pageable);
        return pessoas.map(pessoaMapper::toDTO);
    }

    public PessoaSaidaDTO buscarPessoaPorId(Long id){
        Pessoa pessoa = buscarOuFalhar(id);

        return pessoaMapper.toDTO(pessoa);
    }

    public PessoaSaidaDTO salvarPessoa(PessoaEntradaDTO pessoaEntradaDTO){
        Pessoa pessoa = pessoaMapper.toEntity(pessoaEntradaDTO);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(pessoa);
    }

    public PessoaSaidaDTO atualizarPessoa(PessoaEntradaDTO pessoaEntradaDTO, Long id){
        Pessoa pessoa = buscarOuFalhar(id);
        pessoaMapper.copyToEntity(pessoaEntradaDTO, pessoa);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(pessoa);
    }

    public void removerPessoa(Long id){
        if (!pessoaRepository.existsById(id)){
            throw new RecursoNaoEncontradoException(String.format("Recurso de ID %d nao existe.", id));
        }
        pessoaRepository.deleteById(id);
    }

    public Pessoa buscarOuFalhar(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow( () -> new RecursoNaoEncontradoException(String.format("Recurso de ID %d nao existe.", id)));
    }

}
