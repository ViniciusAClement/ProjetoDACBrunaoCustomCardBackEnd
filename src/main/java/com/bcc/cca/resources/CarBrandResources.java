package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.services.CarBrandService;

@RestController
@RequestMapping("/carbrands")
public class CarBrandResources extends GenericResource<CarBrand, Long>{
	@Autowired
    public CarBrandResources (CarBrandService service) {
        super(service);
    }
}
