package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Admin;
import com.bcc.cca.services.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminResources extends GenericResource<Admin, Long>{
	
	@Autowired
    public AdminResources (AdminService service) {
        super(service);
    }
}
