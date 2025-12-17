package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public ClientService(ClientRepository repository, ClientMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected ClientRepository getRepository() {
        return repository;
    }
}