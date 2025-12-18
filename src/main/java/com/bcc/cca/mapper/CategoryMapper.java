package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.CategoryRequestDTO;
import com.bcc.cca.dto.response.CategoryResponseDTO;
import com.bcc.cca.entites.Category;

@Component
public class CategoryMapper implements GenericMapper<Category, CategoryRequestDTO, CategoryResponseDTO> {
    
    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
    
    public CategoryResponseDTO toResponseDTO(Category entity) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    
    public void updateEntityFromDTO(Category entity, CategoryRequestDTO dto) {
        entity.setName(dto.getName());
    }
}

