package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.repositories.MarketCarRepository;

import jakarta.annotation.PostConstruct;

@Service
public class MarketCarService extends GenericService<MarketCar, Long>{

	@Autowired
	private MarketCarRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	
	@Override
	@Transactional
	public MarketCar create(MarketCar obj) {
		if (obj.getClient() != null && obj.getClient().getId() != null) {
			Client client = clientRepository.findById(obj.getClient().getId())
				.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Client não encontrado com ID: " + obj.getClient().getId()));
			obj.setClient(client);
		}
		return repository.save(obj);
	}

    @Override
    protected void updateData(MarketCar entity, MarketCar newObj) {
        if (newObj.getClient() != null && newObj.getClient().getId() != null) {
        	Client client = clientRepository.findById(newObj.getClient().getId())
        		.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Client não encontrado com ID: " + newObj.getClient().getId()));
        	entity.setClient(client);
        } else if (newObj.getClient() == null) {
        	entity.setClient(null);
        }
    }
}
