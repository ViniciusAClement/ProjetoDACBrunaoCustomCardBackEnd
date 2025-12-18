package com.bcc.cca.mapper;

public interface GenericMapper<Entity, RequestDTO, ResponseDTO> {
    
    Entity toEntity(RequestDTO dto);
    
    ResponseDTO toResponseDTO(Entity entity);
    
    void updateEntityFromDTO(Entity entity, RequestDTO dto);
}










