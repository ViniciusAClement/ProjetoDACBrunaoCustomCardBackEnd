package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.ProductRequestDTO;
import com.bcc.cca.dto.response.ProductResponseDTO;
import com.bcc.cca.entites.Product;
import com.bcc.cca.mapper.ProductMapper;
import com.bcc.cca.repositories.ProductRepository;

@Service
public class ProductService extends GenericServices<Product,ProductRequestDTO,ProductResponseDTO,ProductMapper>{

    private final ProductRepository repository;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected ProductRepository getRepository() {
        return repository;
    }
}