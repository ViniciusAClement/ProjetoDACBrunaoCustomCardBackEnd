package com.bcc.cca.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.ProductRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;
import com.bcc.cca.dto.response.CategoryResponseDTO;
import com.bcc.cca.dto.response.ProductResponseDTO;
import com.bcc.cca.entites.Product;

@Component
public class ProductMapper implements GenericMapper<Product, ProductRequestDTO, ProductResponseDTO> {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Autowired
    private CarMapper carMapper;
    
    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        return product;
    }
    
    public ProductResponseDTO toResponseDTO(Product entity) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setImgUrl(entity.getImgUrl());
        
        if (entity.getCategories() != null && !entity.getCategories().isEmpty()) {
            Set<CategoryResponseDTO> categoryDTOs = entity.getCategories().stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toSet());
            dto.setCategories(categoryDTOs);
        }
        
        if (entity.getCars() != null && !entity.getCars().isEmpty()) {
            Set<CarResponseDTO> carDTOs = entity.getCars().stream()
                .map(carMapper::toResponseDTO)
                .collect(Collectors.toSet());
            dto.setCars(carDTOs);
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(Product entity, ProductRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}

