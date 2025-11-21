package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.repositories.MarketCarItemRepository;

import jakarta.annotation.PostConstruct;

public class MarketCarItemService extends GenericService<MarketCarItem, Long>{

	@Autowired
	private MarketCarItemRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(MarketCarItem entity, MarketCarItem newObj) {
        entity.setProduct(newObj.getProduct());
        entity.setQuantity(newObj.getQuantity());
    }
}
