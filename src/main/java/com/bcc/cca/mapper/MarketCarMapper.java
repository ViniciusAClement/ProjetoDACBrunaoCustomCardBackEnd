package com.bcc.cca.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.MarketCarRequestDTO;
import com.bcc.cca.dto.response.MarketCarItemResponseDTO;
import com.bcc.cca.dto.response.MarketCarResponseDTO;
import com.bcc.cca.entites.MarketCar;

@Component
public class MarketCarMapper implements GenericMapper<MarketCar, MarketCarRequestDTO, MarketCarResponseDTO> {
    
    @Autowired
    private ClientMapper clientMapper;
    
    @Autowired
    private MarketCarItemMapper marketCarItemMapper;
    
    public MarketCar toEntity(MarketCarRequestDTO dto) {
        MarketCar marketCar = new MarketCar();
        marketCar.setId(dto.getId());
        return marketCar;
    }
    
    public MarketCarResponseDTO toResponseDTO(MarketCar entity) {
        MarketCarResponseDTO dto = new MarketCarResponseDTO();
        dto.setId(entity.getId());
        
        if (entity.getClient() != null) {
            dto.setClient(clientMapper.toResponseDTO(entity.getClient()));
        }
        
        if (entity.getMarketCarItens() != null && !entity.getMarketCarItens().isEmpty()) {
            Set<MarketCarItemResponseDTO> itemDTOs = entity.getMarketCarItens().stream()
                .map(marketCarItemMapper::toResponseDTO)
                .collect(Collectors.toSet());
            dto.setMarketCarItens(itemDTOs);
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(MarketCar entity, MarketCarRequestDTO dto) {
        // MarketCar não tem campos atualizáveis além do relacionamento
    }
}

