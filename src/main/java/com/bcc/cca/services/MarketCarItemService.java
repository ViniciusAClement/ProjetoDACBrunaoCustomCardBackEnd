package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.MarketCarItemRequestDTO;
import com.bcc.cca.dto.response.MarketCarItemResponseDTO;
import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.mapper.MarketCarItemMapper;
import com.bcc.cca.repositories.MarketCarItemRepository;

@Service
public class MarketCarItemService extends GenericServices<MarketCarItem,MarketCarItemRequestDTO,MarketCarItemResponseDTO,MarketCarItemMapper>{

    private final MarketCarItemRepository repository;

    public MarketCarItemService(MarketCarItemRepository repository, MarketCarItemMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected MarketCarItemRepository getRepository() {
        return repository;
    }
}