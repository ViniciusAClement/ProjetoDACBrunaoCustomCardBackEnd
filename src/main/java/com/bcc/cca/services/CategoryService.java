package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcc.cca.entites.Category;
import com.bcc.cca.repositories.CategoryRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CategoryService extends GenericService<Category, Long>{

	@Autowired
	private CategoryRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Category entity, Category newObj) {
        entity.setName(newObj.getName());
    }
}
