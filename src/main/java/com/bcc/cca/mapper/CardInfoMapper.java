package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.CardInfoRequestDTO;
import com.bcc.cca.dto.response.CardInfoResponseDTO;
import com.bcc.cca.entites.CardInfo;

@Component
public class CardInfoMapper implements GenericMapper<CardInfo, CardInfoRequestDTO, CardInfoResponseDTO> {
    
    public CardInfo toEntity(CardInfoRequestDTO dto) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setId(dto.getId());
        cardInfo.setCardNumber(dto.getCardNumber());
        cardInfo.setCreditCardOwner(dto.getCreditCardOwner());
        cardInfo.setCardTypeEnum(dto.getCardType());
        return cardInfo;
    }
    
    public CardInfoResponseDTO toResponseDTO(CardInfo entity) {
        CardInfoResponseDTO dto = new CardInfoResponseDTO();
        dto.setId(entity.getId());
        dto.setCardNumber(entity.getCardNumber());
        dto.setCreditCardOwner(entity.getCreditCardOwner());
        dto.setCardType(entity.getCardTypeEnum());
        return dto;
    }
    
    public void updateEntityFromDTO(CardInfo entity, CardInfoRequestDTO dto) {
        entity.setCardNumber(dto.getCardNumber());
        entity.setCreditCardOwner(dto.getCreditCardOwner());
        entity.setCardTypeEnum(dto.getCardType());
    }
}

