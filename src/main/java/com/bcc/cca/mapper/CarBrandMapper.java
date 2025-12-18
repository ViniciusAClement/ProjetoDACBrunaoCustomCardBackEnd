package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.CarBrandRequestDTO;
import com.bcc.cca.dto.response.CarBrandResponseDTO;
import com.bcc.cca.entites.CarBrand;

@Component
public class CarBrandMapper implements GenericMapper<CarBrand, CarBrandRequestDTO, CarBrandResponseDTO> {
    
    public CarBrand toEntity(CarBrandRequestDTO dto) {
        CarBrand carBrand = new CarBrand();
        carBrand.setId(dto.getId());
        carBrand.setName(dto.getName());
        return carBrand;
    }
    
    public CarBrandResponseDTO toResponseDTO(CarBrand entity) {
        CarBrandResponseDTO dto = new CarBrandResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    
    public void updateEntityFromDTO(CarBrand entity, CarBrandRequestDTO dto) {
        entity.setName(dto.getName());
    }
}

