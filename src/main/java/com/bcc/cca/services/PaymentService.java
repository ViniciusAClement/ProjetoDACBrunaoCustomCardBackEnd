package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.PaymentRequestDTO;
import com.bcc.cca.dto.response.PaymentResponseDTO;
import com.bcc.cca.entites.Payment;
import com.bcc.cca.mapper.PaymentMapper;
import com.bcc.cca.repositories.PaymentRepository;

@Service
public class PaymentService extends GenericServices<Payment,PaymentRequestDTO,PaymentResponseDTO,PaymentMapper>{

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository, PaymentMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected PaymentRepository getRepository() {
        return repository;
    }
}