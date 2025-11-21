package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.cca.entites.Payment;
import com.bcc.cca.repositories.PaymentRepository;

import jakarta.annotation.PostConstruct;

public class PaymentService extends GenericService<Payment, Long>{

	@Autowired
	private PaymentRepository repository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	

    @Override
    protected void updateData(Payment entity, Payment newObj) {
        entity.setInstant(newObj.getInstant());
        entity.setPaymentMethod(newObj.getPaymentMethod());
    }
}
