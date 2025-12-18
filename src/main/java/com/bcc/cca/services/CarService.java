package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.dto.request.CarRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;
import com.bcc.cca.entites.Car;
import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.repositories.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.CarRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;
import com.bcc.cca.entites.Car;
import com.bcc.cca.mapper.CarMapper;
import com.bcc.cca.repositories.CarRepository;

@Service
public class CarService extends GenericServices<Car,CarRequestDTO,CarResponseDTO,CarMapper>{

    private final CarRepository repository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    public CarService(CarRepository repository, CarMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CarRepository getRepository() {
        return repository;
    }

    @Override
    public CarResponseDTO create(CarRequestDTO dto){
        CarBrand CarBrand = carBrandRepository.findById(dto.getCarBrandId()).orElseThrow(() -> new RuntimeException("Marca de Carro Inexistente"));
        Car entity = mapper.toEntity(dto);

        entity.setCarBrand(CarBrand);

        getRepository().save(entity);
        return mapper.toResponseDTO(entity);
    }
}