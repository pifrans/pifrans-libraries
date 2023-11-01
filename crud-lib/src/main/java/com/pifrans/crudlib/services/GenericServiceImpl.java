package com.pifrans.crudlib.services;


import com.pifrans.commonutilslib.strings.StringTools;
import com.pifrans.crudlib.errors.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericServiceImpl<E, I> implements GenericService<E, I> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericServiceImpl.class);
    private final JpaRepository<E, I> repository;


    protected GenericServiceImpl(JpaRepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public E findById(Class<E> tClass, I id) {
        var entity = repository.findById(id);

        if (entity.isPresent()) {
            return entity.get();
        }

        var message = StringTools.format("Nenhum item {} de ID {} encontrado!", tClass.getSimpleName(), id);
        LOG.error(message);
        throw new NotFoundException(message);
    }

    @Override
    public E save(E object) throws DataIntegrityViolationException {
        return repository.save(object);
    }

    @Override
    public List<E> saveAll(List<E> list) throws DataIntegrityViolationException {
        return repository.saveAll(list);
    }

    @Override
    public E update(E object, I id) throws DataIntegrityViolationException {
        if (repository.existsById(id)) {
            return repository.save(object);
        }

        var message = StringTools.format("Não foi possível atualizar {} de ID {}, não encontrado!", object.getClass().getSimpleName(), id);
        LOG.error(message);
        throw new NotFoundException(message);
    }

    @Override
    public E deleteById(Class<E> tClass, I id) {
        var entity = repository.findById(id);

        if (entity.isEmpty()) {
            var message = StringTools.format("Não foi possível excluir o item, pois não existe {} com ID {}!", tClass.getSimpleName(), id);
            LOG.error(message);
            throw new NotFoundException(message);
        }

        repository.deleteById(id);
        return entity.get();
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}