package com.bcc.cca.mapper;

import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.PaymentRequestDTO;
import com.bcc.cca.dto.response.PaymentResponseDTO;
import com.bcc.cca.entites.Payment;

@Component
public class PaymentMapper implements GenericMapper<Payment, PaymentRequestDTO, PaymentResponseDTO> {
    
    public Payment toEntity(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setPaymentMethodEnum(dto.getPaymentMethod());
        payment.setAmount(dto.getAmount());
        return payment;
    }
    
    public PaymentResponseDTO toResponseDTO(Payment entity) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(entity.getId());
        dto.setInstant(entity.getInstant());
        dto.setPaymentMethod(entity.getPaymentMethodEnum());
        dto.setAmount(entity.getAmount());
        return dto;
    }
    
    public void updateEntityFromDTO(Payment entity, PaymentRequestDTO dto) {
        entity.setPaymentMethodEnum(dto.getPaymentMethod());
        entity.setAmount(dto.getAmount());
    }
}

