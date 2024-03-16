package com.tauan.txbank.core.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<I,E,O>{

    O toDTO(E entity);

    E toEntity(I entrada);

    void copyToEntity(I entrada, @MappingTarget E entity);

}