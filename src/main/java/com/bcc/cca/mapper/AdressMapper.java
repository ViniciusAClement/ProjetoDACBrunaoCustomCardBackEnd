package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.AdressRequestDTO;
import com.bcc.cca.dto.response.AdressResponseDTO;
import com.bcc.cca.entites.Adress;

@Component
public class AdressMapper implements GenericMapper<Adress, AdressRequestDTO, AdressResponseDTO> {
    
    public Adress toEntity(AdressRequestDTO dto) {
        Adress adress = new Adress();
        adress.setId(dto.getId());
        adress.setStreet(dto.getStreet());
        adress.setNumber(dto.getNumber());
        adress.setZipcode(dto.getZipcode());
        adress.setState(dto.getState());
        adress.setCity(dto.getCity());
        return adress;
    }
    
    public AdressResponseDTO toResponseDTO(Adress entity) {
        AdressResponseDTO dto = new AdressResponseDTO();
        dto.setId(entity.getId());
        dto.setStreet(entity.getStreet());
        dto.setNumber(entity.getNumber());
        dto.setZipcode(entity.getZipcode());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        return dto;
    }
    
    public void updateEntityFromDTO(Adress entity, AdressRequestDTO dto) {
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setZipcode(dto.getZipcode());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
    }
}

