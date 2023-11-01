package com.pifrans.crudlib.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<E, I> {

    E findById(Class<E> tClass, I id);

    E save(E object) throws DataIntegrityViolationException;

    List<E> saveAll(List<E> list) throws DataIntegrityViolationException;

    E update(E object, I id) throws DataIntegrityViolationException;

    E deleteById(Class<E> tClass, I id);

    List<E> findAll();

    Page<E> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
