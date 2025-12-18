package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ProductResponseDTO create(ProductRequestDTO dto){
        Product product = mapper.toEntity(dto);
        repository.save(product);

        ArrayList<Category> categories = new ArrayList<>();
        for ( Long ids : dto.getCategoryIds()){
            categories.add(categoryRepository.findById(ids).orElseThrow(() -> new RuntimeException("Categoria Inexistente")));
        }

        ArrayList<Car> cars = new ArrayList<>();
        for ( Long ids : dto.getCarIds()){
            cars.add(carRepository.findById(ids).orElseThrow(() -> new RuntimeException("Carro Inexistente")));
        }

        for ( Category category: categories){
            category.addProduct(product);
            product.addCategory(category);
        }

        for ( Car car : cars){
            car.addProduct(product);
            product.addCar(car);
        }

        repository.save(product);
        return mapper.toResponseDTO(product);
    }
}