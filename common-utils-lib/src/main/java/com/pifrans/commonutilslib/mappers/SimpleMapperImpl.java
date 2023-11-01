package com.pifrans.commonutilslib.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SimpleMapperImpl implements SimpleMapper {
    private final ModelMapper modelMapper;


    public SimpleMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <E, D> D toDto(E objectEntity, Class<D> classDto) {
        return modelMapper.map(objectEntity, classDto);
    }

    @Override
    public <E, D> E toEntity(D objectDto, Class<E> classEntity) {
        return modelMapper.map(objectDto, classEntity);
    }
}
