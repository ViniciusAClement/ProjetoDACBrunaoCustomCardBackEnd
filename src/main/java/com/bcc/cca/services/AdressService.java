package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Adress;
import com.bcc.cca.repositories.AdressRepository;

import jakarta.annotation.PostConstruct;

public class AdressService extends GenericService<Adress, Long>{
	@Autowired
	private AdressRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Adress entity, Adress newObj) {
        entity.setStreet(newObj.getStreet());
        entity.setNumber(newObj.getNumber());
        entity.setState(newObj.getState());
        entity.setZipcode(newObj.getZipcode());
        entity.setCity(newObj.getCity());
    }
}
