package com.bcc.cca.services;

import com.bcc.cca.Exceptions.ConflictException;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.repositories.MarketCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private MarketCarRepository marketCarRepository;

    public ClientService(ClientRepository repository, ClientMapper mapper, PasswordEncoder passwordEncoder) {
        super(mapper);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        marketCar.setClient(entity);
        marketCarRepository.save(marketCar);

        entity.setMarketCar(marketCar);
        getRepository().save(entity);
        return mapper.toResponseDTO(entity);
    }
}