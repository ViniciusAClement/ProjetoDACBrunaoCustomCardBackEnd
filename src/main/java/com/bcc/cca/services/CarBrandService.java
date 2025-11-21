package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.repositories.CarBrandRepository;

import jakarta.annotation.PostConstruct;

public class CarBrandService extends GenericService<CarBrand, Long>{
	@Autowired
	private CarBrandRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(CarBrand entity, CarBrand newObj) {
        entity.setName(newObj.getName());
    }
}
