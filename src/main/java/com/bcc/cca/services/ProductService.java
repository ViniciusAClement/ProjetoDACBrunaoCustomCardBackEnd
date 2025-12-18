package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.EntityNotFoundException;
import com.bcc.cca.entites.Car;
import com.bcc.cca.entites.Category;
import com.bcc.cca.repositories.CarRepository;
import com.bcc.cca.repositories.CategoryRepository;
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

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected ProductRepository getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {

        Product product = mapper.toEntity(dto);

        for (Long id : dto.getCategoryIds()) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria Inexistente"));
            product.addCategory(category);
        }

        for (Long id : dto.getCarIds()) {
            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Carro Inexistente"));
            product.addCar(car);
        }

        repository.save(product);
        return mapper.toResponseDTO(product);
    }

}