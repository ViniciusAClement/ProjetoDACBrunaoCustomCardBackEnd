package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.EntityNotFoundException;
import com.bcc.cca.entites.Client;
import com.bcc.cca.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.MarketCarRequestDTO;
import com.bcc.cca.dto.response.MarketCarResponseDTO;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.mapper.MarketCarMapper;
import com.bcc.cca.repositories.MarketCarRepository;

@Service
public class MarketCarService extends GenericServices<MarketCar,MarketCarRequestDTO,MarketCarResponseDTO,MarketCarMapper>{

    private final MarketCarRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    public MarketCarService(MarketCarRepository repository, MarketCarMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected MarketCarRepository getRepository() {
        return repository;
    }

}