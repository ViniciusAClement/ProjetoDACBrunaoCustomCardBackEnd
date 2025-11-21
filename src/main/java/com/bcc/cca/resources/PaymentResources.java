package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Payment;
import com.bcc.cca.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentResources extends GenericResource<Payment, Long>{
	@Autowired
    public PaymentResources (PaymentService service) {
        super(service);
    }
}
