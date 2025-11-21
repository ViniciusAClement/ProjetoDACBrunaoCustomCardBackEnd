package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Product;
import com.bcc.cca.repositories.ProductRepository;

import jakarta.annotation.PostConstruct;

public class ProductService extends GenericService<Product, Long>{

	@Autowired
	private ProductRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Product entity, Product newObj) {
        entity.setDescription(newObj.getDescription());
        entity.setImgUrl(newObj.getImgUrl());
        entity.setName(newObj.getName());
        entity.setPrice(newObj.getPrice());
    }
}
