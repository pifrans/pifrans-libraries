package com.pifrans.starterlib.rest.controllers;


import com.pifrans.commonutilslib.mappers.SimpleMapper;
import com.pifrans.starterlib.rest.responses.SuccessResponse;
import com.pifrans.starterlib.services.GenericService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class GenericControllerImpl<E, C, I> implements GenericController<E, I> {
    private final GenericService<E, I> service;
    private final Class<E> classModel;
    private final Class<C> classController;
    private final HttpServletRequest request;
    private final SimpleMapper simpleMapper;


    protected GenericControllerImpl(GenericService<E, I> service, Class<E> classModel, Class<C> classController, HttpServletRequest request, SimpleMapper simpleMapper) {
        this.service = service;
        this.classModel = classModel;
        this.classController = classController;
        this.request = request;
        this.simpleMapper = simpleMapper;
    }

    @Override
    public <D> ResponseEntity<D> findById(Class<D> classDto, I id) {
        var entity = service.findById(classModel, id);
        var dto = simpleMapper.toDto(entity, classDto);
        return new SuccessResponse<D>().handle(dto, classController, request, HttpStatus.OK);
    }

    @Override
    public <D> ResponseEntity<List<D>> findAll(Class<D> classDto) {
        var entities = service.findAll();
        var dtos = entities.stream().map(e -> simpleMapper.toDto(e, classDto)).toList();
        return new SuccessResponse<List<D>>().handle(dtos, classController, request, HttpStatus.OK);
    }

    @Override
    public <D> ResponseEntity<Page<D>> findByPage(Class<D> classDto, Integer page, Integer linesPerPage, String orderBy, String direction) {
        var entityPages = service.findByPage(page, linesPerPage, orderBy, direction);
        var entities = entityPages.getContent();
        var dtos = entities.stream().map(e -> simpleMapper.toDto(e, classDto)).toList();
        var dtoPages = new PageImpl<>(dtos, entityPages.getPageable(), entityPages.getTotalElements());
        return ResponseEntity.ok().body(dtoPages);
    }

    @Override
    public <D> ResponseEntity<D> save(Class<D> classDto, E body) {
        var entity = service.save(body);
        var dto = simpleMapper.toDto(entity, classDto);
        return new SuccessResponse<D>().handle(dto, classController, request, HttpStatus.CREATED);
    }

    @Override
    public <D> ResponseEntity<List<D>> saveAll(Class<D> classDto, List<E> body) {
        var entities = service.saveAll(body);
        var dtos = entities.stream().map(e -> simpleMapper.toDto(e, classDto)).toList();
        return new SuccessResponse<List<D>>().handle(dtos, classController, request, HttpStatus.CREATED);
    }

    @Override
    public <D> ResponseEntity<D> update(Class<D> classDto, E body, I id) {
        var entity = service.update(body, id);
        var dto = simpleMapper.toDto(entity, classDto);
        return new SuccessResponse<D>().handle(dto, classController, request, HttpStatus.OK);
    }

    @Override
    public <D> ResponseEntity<D> deleteById(Class<D> classDto, I id) {
        var entity = service.deleteById(classModel, id);
        var dto = simpleMapper.toDto(entity, classDto);
        return new SuccessResponse<D>().handle(dto, classController, request, HttpStatus.OK);
    }
}