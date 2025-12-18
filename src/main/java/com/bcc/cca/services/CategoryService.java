package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.CategoryRequestDTO;
import com.bcc.cca.dto.response.CategoryResponseDTO;
import com.bcc.cca.entites.Category;
import com.bcc.cca.mapper.CategoryMapper;
import com.bcc.cca.repositories.CategoryRepository;

@Service
public class CategoryService extends GenericServices<Category,CategoryRequestDTO,CategoryResponseDTO,CategoryMapper>{

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected CategoryRepository getRepository() {
        return repository;
    }

}