package com.bcc.cca.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public abstract class GenericService<T, ID> {

    @Autowired
    protected org.springframework.data.jpa.repository.JpaRepository<T, ID> repository;

    public T create(T obj) {
        return repository.save(obj);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(ID id) {
        Optional<T> obj = repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public T update(ID id, T newObj) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Objeto não encontrado para atualização! Id: " + id));
        updateData(entity, newObj);

        return repository.save(entity);
    }

    protected abstract void updateData(T entity, T newObj);

    public void delete(ID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Objeto não encontrado para exclusão! Id: " + id);
        }
    }
}
