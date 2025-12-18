package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.CarBrandRequestDTO;
import com.bcc.cca.dto.response.CarBrandResponseDTO;
import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.mapper.CarBrandMapper;
import com.bcc.cca.repositories.CarBrandRepository;

@Service
public class CarBrandService extends GenericServices<CarBrand,CarBrandRequestDTO,CarBrandResponseDTO,CarBrandMapper>{

    private final CarBrandRepository repository;

    public CarBrandService(CarBrandRepository repository, CarBrandMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CarBrandRepository getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public CarBrandResponseDTO create(CarBrandRequestDTO dto){
        CarBrand carBrand = mapper.toEntity(dto);

        if (repository.existsByName(carBrand.getName())){
            throw new ConflictException("Marca já existente");
        }

        repository.save(carBrand);
        return mapper.toResponseDTO(carBrand);
    }
}