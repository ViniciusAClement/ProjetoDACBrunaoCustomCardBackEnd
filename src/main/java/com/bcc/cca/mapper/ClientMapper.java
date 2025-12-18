package com.bcc.cca.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.ClientRequestDTO;
import com.bcc.cca.dto.response.AdressResponseDTO;
import com.bcc.cca.dto.response.CardInfoResponseDTO;
import com.bcc.cca.dto.response.ClientResponseDTO;
import com.bcc.cca.entites.Client;

@Component
public class ClientMapper implements GenericMapper<Client, ClientRequestDTO, ClientResponseDTO> {
    
    @Autowired
    private AdressMapper adressMapper;
    
    @Autowired
    private CardInfoMapper cardInfoMapper;
    
    public Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setPassword(dto.getPassword());
        client.setCpf(dto.getCpf());
        return client;
    }
    
    public ClientResponseDTO toResponseDTO(Client entity) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCpf(entity.getCpf());
        
        if (entity.getAdresses() != null && !entity.getAdresses().isEmpty()) {
            Set<AdressResponseDTO> adressDTOs = entity.getAdresses().stream()
                .map(adressMapper::toResponseDTO)
                .collect(Collectors.toSet());
            dto.setAdresses(adressDTOs);
        }
        
        if (entity.getCardInfo() != null && !entity.getCardInfo().isEmpty()) {
            Set<CardInfoResponseDTO> cardInfoDTOs = entity.getCardInfo().stream()
                .map(cardInfoMapper::toResponseDTO)
                .collect(Collectors.toSet());
            dto.setCardInfo(cardInfoDTOs);
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(Client entity, ClientRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            entity.setPassword(dto.getPassword());
        }
        entity.setCpf(dto.getCpf());
    }
}

