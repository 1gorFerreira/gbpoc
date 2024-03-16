package com.tauan.txbank.feature.Pessoa.mapper;

import com.tauan.txbank.core.mapper.BaseMapper;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaEntradaDTO;
import com.tauan.txbank.feature.Pessoa.model.dto.PessoaSaidaDTO;
import com.tauan.txbank.feature.Pessoa.model.entities.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface PessoaMapper extends BaseMapper<PessoaEntradaDTO, Pessoa, PessoaSaidaDTO> {

}
