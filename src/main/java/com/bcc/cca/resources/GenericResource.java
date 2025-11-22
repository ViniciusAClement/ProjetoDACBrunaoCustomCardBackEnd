package com.bcc.cca.resources;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bcc.cca.services.GenericService;

import jakarta.persistence.EntityNotFoundException;

public abstract class GenericResource<T, ID> {

    protected GenericService<T, ID> service;
    
    protected GenericResource(GenericService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        List<T> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable("id") ID id) {
        try {
            T obj = service.findById(id);
            return ResponseEntity.ok().body(obj);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<T> insert(@RequestBody T obj) {
        obj = service.create(obj);

        ID id = extractId(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable("id") ID id, @RequestBody T obj) {
        try {
            obj = service.update(id, obj);
            return ResponseEntity.ok().body(obj);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @SuppressWarnings("unchecked")
    private ID extractId(T obj) {
        try {
            Method getId = obj.getClass().getMethod("getId");
            return (ID) getId.invoke(obj);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                "A classe " + obj.getClass().getSimpleName() + " precisa ter um método getId() público.", e
            );
        } catch (Exception e) {
            throw new RuntimeException(
                "Erro ao extrair ID do objeto " + obj.getClass().getSimpleName() + ": " + e.getMessage(), e
            );
        }
    }
}
