package com.bcc.cca.services;

import com.bcc.cca.entites.Client;
import com.bcc.cca.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcc.cca.dto.request.CardInfoRequestDTO;
import com.bcc.cca.dto.response.CardInfoResponseDTO;
import com.bcc.cca.entites.CardInfo;
import com.bcc.cca.mapper.CardInfoMapper;
import com.bcc.cca.repositories.CardInfoRepository;

@Service
public class CardInfoService extends GenericServices<CardInfo,CardInfoRequestDTO,CardInfoResponseDTO,CardInfoMapper>{

    private final CardInfoRepository repository;

    @Autowired
    private ClientRepository clientrepo;

    public CardInfoService(CardInfoRepository repository, CardInfoMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CardInfoRepository getRepository() {
        return repository;
    }

    @Override
    public CardInfoResponseDTO create(CardInfoRequestDTO dto){
        Client client = clientrepo.findById(dto.getClientId()).orElseThrow(() -> new RuntimeException("Cliente Inexistente"));
        CardInfo entity = mapper.toEntity(dto);

        entity.setClient(client);

        getRepository().save(entity);
        return mapper.toResponseDTO(entity);
    }
}