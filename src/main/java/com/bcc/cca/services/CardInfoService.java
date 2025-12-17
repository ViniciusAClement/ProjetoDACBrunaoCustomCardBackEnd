package com.bcc.cca.services;

import org.springframework.stereotype.Service;

import com.bcc.cca.dto.request.CardInfoRequestDTO;
import com.bcc.cca.dto.response.CardInfoResponseDTO;
import com.bcc.cca.entites.CardInfo;
import com.bcc.cca.mapper.CardInfoMapper;
import com.bcc.cca.repositories.CardInfoRepository;

@Service
public class CardInfoService extends GenericServices<CardInfo,CardInfoRequestDTO,CardInfoResponseDTO,CardInfoMapper>{

    private final CardInfoRepository repository;

    public CardInfoService(CardInfoRepository repository, CardInfoMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CardInfoRepository getRepository() {
        return repository;
    }
}