package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Order;
import com.bcc.cca.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResources extends GenericResource<Order, Long>{
	@Autowired
    public OrderResources (OrderService service) {
        super(service);
    }
}
