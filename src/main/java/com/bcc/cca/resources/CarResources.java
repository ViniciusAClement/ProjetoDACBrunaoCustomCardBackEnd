package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Car;
import com.bcc.cca.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarResources extends GenericResource<Car, Long>{
	@Autowired
    public CarResources (CarService service) {
        super(service);
    }
}
