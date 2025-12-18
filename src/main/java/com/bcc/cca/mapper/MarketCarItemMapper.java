package com.bcc.cca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.MarketCarItemRequestDTO;
import com.bcc.cca.dto.response.MarketCarItemResponseDTO;
import com.bcc.cca.entites.MarketCarItem;

@Component
public class MarketCarItemMapper implements GenericMapper<MarketCarItem, MarketCarItemRequestDTO, MarketCarItemResponseDTO> {
    
    @Autowired
    private ProductMapper productMapper;
    
    public MarketCarItem toEntity(MarketCarItemRequestDTO dto) {
        MarketCarItem item = new MarketCarItem();
        item.setId(dto.getId());
        item.setQuantity(dto.getQuantity());
        return item;
    }
    
    public MarketCarItemResponseDTO toResponseDTO(MarketCarItem entity) {
        MarketCarItemResponseDTO dto = new MarketCarItemResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        
        if (entity.getProduct() != null) {
            dto.setProduct(productMapper.toResponseDTO(entity.getProduct()));
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(MarketCarItem entity, MarketCarItemRequestDTO dto) {
        entity.setQuantity(dto.getQuantity());
    }
}

