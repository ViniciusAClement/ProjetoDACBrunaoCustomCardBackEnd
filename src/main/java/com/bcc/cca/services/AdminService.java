package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Admin;
import com.bcc.cca.repositories.AdminRepository;

import jakarta.annotation.PostConstruct;

public class AdminService extends GenericService<Admin, Long>{
	
	@Autowired
	private AdminRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Admin entity, Admin newObj) {
        entity.setName(newObj.getName());
        entity.setEmail(newObj.getEmail());
        entity.setPhone(newObj.getPhone());
        entity.setCpf(newObj.getCpf());
    }
}
