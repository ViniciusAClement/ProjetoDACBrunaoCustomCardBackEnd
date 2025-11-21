package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Client;
import com.bcc.cca.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResources extends GenericResource<Client, Long>{
	@Autowired
    public ClientResources (ClientService service) {
        super(service);
    }
}
