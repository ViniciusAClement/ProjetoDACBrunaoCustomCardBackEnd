package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.User;
import com.bcc.cca.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

public class UserService extends GenericService<User, Long>{

	@Autowired
	private UserRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(User entity, User newObj) {
    	entity.setName(newObj.getName());
        entity.setEmail(newObj.getEmail());
        entity.setPhone(newObj.getPhone());
        entity.setCpf(newObj.getCpf());
    }
}
