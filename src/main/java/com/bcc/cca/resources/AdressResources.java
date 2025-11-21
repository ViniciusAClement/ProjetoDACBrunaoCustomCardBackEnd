package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Adress;
import com.bcc.cca.services.AdressService;

@RestController
@RequestMapping("/addresses")
public class AdressResources extends GenericResource<Adress, Long>{
	@Autowired
    public AdressResources (AdressService service) {
        super(service);
    }
}
