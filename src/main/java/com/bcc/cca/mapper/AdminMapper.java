package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.AdminRequestDTO;
import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.entites.Admin;

@Component
public class AdminMapper implements GenericMapper<Admin, AdminRequestDTO, AdminResponseDTO> {
    
    public Admin toEntity(AdminRequestDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPhone(dto.getPhone());
        admin.setPassword(dto.getPassword());
        admin.setCpf(dto.getCpf());
        return admin;
    }
    
    public AdminResponseDTO toResponseDTO(Admin entity) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCpf(entity.getCpf());
        return dto;
    }
    
    public void updateEntityFromDTO(Admin entity, AdminRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            entity.setPassword(dto.getPassword());
        }
        entity.setCpf(dto.getCpf());
    }
}

