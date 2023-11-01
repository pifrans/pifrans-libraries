package com.pifrans.commonutilslib.mappers;

public interface SimpleMapper {


    <E, D> D toDto(E objectEntity, Class<D> classDto);

    <E, D> E toEntity(D objectDto, Class<E> classEntity);
}
