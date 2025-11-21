package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Car;
import com.bcc.cca.repositories.CarRepository;

import jakarta.annotation.PostConstruct;

public class CarService extends GenericService<Car, Long>{

	@Autowired
	private CarRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Car entity, Car newObj) {
        entity.setName(newObj.getName());
        entity.setYearOfCar(newObj.getYearOfCar());
        entity.setCarBrand(newObj.getCarBrand());
    }
}
