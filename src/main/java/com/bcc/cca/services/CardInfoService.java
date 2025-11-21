package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.CardInfo;
import com.bcc.cca.repositories.CardInfoRepository;

import jakarta.annotation.PostConstruct;

public class CardInfoService extends GenericService<CardInfo, Long>{

	@Autowired
	private CardInfoRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(CardInfo entity, CardInfo newObj) {
        entity.setCardNumber(newObj.getCardNumber());
        entity.setCreditCardOwner(newObj.getCreditCardOwner());
        entity.setCardType(newObj.getCardType());
    }
}
