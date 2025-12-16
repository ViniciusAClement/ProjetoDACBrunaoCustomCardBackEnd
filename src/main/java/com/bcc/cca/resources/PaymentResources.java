package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.PaymentRequestDTO;
import com.bcc.cca.dto.response.PaymentResponseDTO;
import com.bcc.cca.entites.Payment;
import com.bcc.cca.mapper.PaymentMapper;
import com.bcc.cca.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentResources extends GenericResourceDTO<Payment, Long, PaymentRequestDTO, PaymentResponseDTO> {
	@Autowired
    public PaymentResources(PaymentService service, PaymentMapper mapper) {
        super(service, mapper);
    }
}
