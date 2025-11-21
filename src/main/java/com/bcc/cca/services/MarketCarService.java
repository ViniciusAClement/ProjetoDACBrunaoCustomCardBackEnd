package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.repositories.MarketCarRepository;

import jakarta.annotation.PostConstruct;

public class MarketCarService extends GenericService<MarketCar, Long>{

	@Autowired
	private MarketCarRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(MarketCar entity, MarketCar newObj) {
        entity.setClient(newObj.getClient());
    }
}
