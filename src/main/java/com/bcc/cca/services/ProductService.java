package com.bcc.cca.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.entites.Car;
import com.bcc.cca.entites.Category;
import com.bcc.cca.entites.Product;
import com.bcc.cca.repositories.CarRepository;
import com.bcc.cca.repositories.CategoryRepository;
import com.bcc.cca.repositories.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService extends GenericService<Product, Long>{

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	
	@Override
	@Transactional
	public Product create(Product obj) {
		Product savedProduct = repository.save(obj);
		
		if (obj.getCategories() != null && !obj.getCategories().isEmpty()) {
			Set<Category> categoriesToAdd = new HashSet<>();
			for (Category category : obj.getCategories()) {
				if (category.getId() != null) {
					Category foundCategory = categoryRepository.findById(category.getId())
						.orElse(null);
					if (foundCategory != null) {
						categoriesToAdd.add(foundCategory);
						foundCategory.getProducts().add(savedProduct);
					}
				}
			}
			savedProduct.getCategories().clear();
			savedProduct.getCategories().addAll(categoriesToAdd);
		}
		
		if (obj.getCars() != null && !obj.getCars().isEmpty()) {
			Set<Car> carsToAdd = new HashSet<>();
			for (Car car : obj.getCars()) {
				if (car.getId() != null) {
					Car foundCar = carRepository.findById(car.getId())
						.orElse(null);
					if (foundCar != null) {
						carsToAdd.add(foundCar);
						foundCar.getProducts().add(savedProduct);
					}
				}
			}
			savedProduct.getCars().clear();
			savedProduct.getCars().addAll(carsToAdd);
		}
		
		return repository.save(savedProduct);
	}

    @Override
    protected void updateData(Product entity, Product newObj) {
        entity.setDescription(newObj.getDescription());
        entity.setImgUrl(newObj.getImgUrl());
        entity.setName(newObj.getName());
        entity.setPrice(newObj.getPrice());
        
        if (newObj.getCategories() != null) {
        	for (Category oldCategory : entity.getCategories()) {
        		oldCategory.getProducts().remove(entity);
        	}
        	
        	entity.getCategories().clear();
        	for (Category category : newObj.getCategories()) {
        		if (category.getId() != null) {
        			Category foundCategory = categoryRepository.findById(category.getId())
        				.orElse(null);
        			if (foundCategory != null) {
        				entity.getCategories().add(foundCategory);
        				foundCategory.getProducts().add(entity);
        			}
        		}
        	}
        }
        
        if (newObj.getCars() != null) {
        	for (Car oldCar : entity.getCars()) {
        		oldCar.getProducts().remove(entity);
        	}
        	
        	entity.getCars().clear();
        	for (Car car : newObj.getCars()) {
        		if (car.getId() != null) {
        			Car foundCar = carRepository.findById(car.getId())
        				.orElse(null);
        			if (foundCar != null) {
        				entity.getCars().add(foundCar);
        				foundCar.getProducts().add(entity);
        			}
        		}
        	}
        }
    }
}
