package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.entites.Client;
import com.bcc.cca.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.AdressRequestDTO;
import com.bcc.cca.dto.response.AdressResponseDTO;
import com.bcc.cca.entites.Adress;
import com.bcc.cca.mapper.AdressMapper;
import com.bcc.cca.repositories.AdressRepository;

@Service
public class AdressService extends GenericServices<Adress,AdressRequestDTO,AdressResponseDTO,AdressMapper>{

    private final AdressRepository repository;

    @Autowired
    private ClientRepository clientrepo;

    public AdressService(AdressRepository repository, AdressMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected AdressRepository getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public AdressResponseDTO create(AdressRequestDTO dto){
        Client client = clientrepo.findById(dto.getClientId()).orElseThrow(() -> new RuntimeException("Cliente Não Existe"));
        Adress entity = mapper.toEntity(dto);

        entity.setClient(client);

        getRepository().save(entity);

        client.addAdress(entity);
        clientrepo.save(client);

        return mapper.toResponseDTO(entity);
    }
}