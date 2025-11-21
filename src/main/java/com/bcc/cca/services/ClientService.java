package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Client;
import com.bcc.cca.repositories.ClientRepository;

import jakarta.annotation.PostConstruct;

public class ClientService extends GenericService<Client, Long>{

	@Autowired
	private ClientRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Client entity, Client newObj) {
        entity.setName(newObj.getName());
        entity.setEmail(newObj.getEmail());
        entity.setCpf(newObj.getCpf());
        entity.setPhone(newObj.getPhone());
    }
}
