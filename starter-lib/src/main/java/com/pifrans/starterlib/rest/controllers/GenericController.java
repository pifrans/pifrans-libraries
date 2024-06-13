package com.pifrans.starterlib.rest.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<E, I> {

    <D> ResponseEntity<D> findById(Class<D> classDto, I id);

    <D> ResponseEntity<List<D>> findAll(Class<D> classDto);

    <D> ResponseEntity<Page<D>> findByPage(Class<D> classDto, Integer page, Integer linesPerPage, String orderBy, String direction);

    <D> ResponseEntity<D> save(Class<D> classDto, E body);

    <D> ResponseEntity<List<D>> saveAll(Class<D> classDto, List<E> body);

    <D> ResponseEntity<D> update(Class<D> classDto, E body, I id);

    <D> ResponseEntity<D> deleteById(Class<D> classDto, I id);
}
