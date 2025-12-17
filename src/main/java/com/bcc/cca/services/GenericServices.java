package com.bcc.cca.services;

import com.bcc.cca.mapper.GenericMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericServices <E,Req,Res, M extends GenericMapper<E,Req,Res>> {

    protected final M mapper;

    protected GenericServices(M mapper){
        this.mapper = mapper;
    }

    protected abstract JpaRepository<E,Long> getRepository();

    public List<Res> findAll() {
        List<E> list = getRepository().findAll();
        List<Res> responseList = new ArrayList<>();
        for (E entity : list) {
            responseList.add(mapper.toResponseDTO(entity));
        }
        return responseList;
    }

    public Res findById(Long id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        return mapper.toResponseDTO(entity);
    }

    public Res create(Req dto) {
        E entity = mapper.toEntity(dto);
        getRepository().save(entity);
        return mapper.toResponseDTO(entity);
    }

    @Transactional
    public Res update(Long id, Req dto) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        mapper.updateEntityFromDTO(entity, dto);
        getRepository().save(entity);

        return mapper.toResponseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        getRepository().delete(entity);
    }
}
