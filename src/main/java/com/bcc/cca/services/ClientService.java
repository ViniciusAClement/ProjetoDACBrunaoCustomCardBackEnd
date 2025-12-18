package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.ConflictException;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.repositories.MarketCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.ClientRequestDTO;
import com.bcc.cca.dto.response.ClientResponseDTO;
import com.bcc.cca.entites.Client;
import com.bcc.cca.mapper.ClientMapper;
import com.bcc.cca.repositories.ClientRepository;

@Service
public class ClientService extends GenericServices<Client,ClientRequestDTO,ClientResponseDTO,ClientMapper>{

    private final ClientRepository repository;

    @Autowired
    private MarketCarRepository marketCarRepository;

    public ClientService(ClientRepository repository, ClientMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected ClientRepository getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public ClientResponseDTO create(ClientRequestDTO dto){
        Client entity = mapper.toEntity(dto);
        MarketCar marketCar = new MarketCar();

        if (repository.existsByEmail(entity.getEmail())){
            throw new ConflictException("Email ja cadastrado");
        }

        if (repository.existsByCpf(entity.getCpf())){
            throw new ConflictException("CPF já cadastrado");
        }

        marketCar.setClient(entity);
        marketCarRepository.save(marketCar);

        entity.setMarketCar(marketCar);
        getRepository().save(entity);
        return mapper.toResponseDTO(entity);
    }
}