package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public CarService(CarRepository repository, CarMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CarRepository getRepository() {
        return repository;
    }
}