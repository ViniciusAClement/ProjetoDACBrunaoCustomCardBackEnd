package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcc.cca.entites.Order;
import com.bcc.cca.repositories.OrderRepository;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService extends GenericService<Order, Long>{

	@Autowired
	private OrderRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Order entity, Order newObj) {
        entity.setClient(newObj.getClient());
        entity.setDeliveryStatus(newObj.getDeliveryStatus());
        entity.setInstant(newObj.getInstant());
        entity.setPayment(newObj.getPayment());
        entity.setPaymentStatus(newObj.getPaymentStatus());
    }
}
