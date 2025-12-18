package com.bcc.cca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.OrderRequestDTO;
import com.bcc.cca.dto.response.OrderResponseDTO;
import com.bcc.cca.entites.Order;

@Component
public class OrderMapper implements GenericMapper<Order, OrderRequestDTO, OrderResponseDTO> {
    
    @Autowired
    private ClientMapper clientMapper;
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    public Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setPaymentStatusEnum(dto.getPaymentStatus());
        order.setDeliveryStatusEnum(dto.getDeliveryStatus());
        return order;
    }
    
    public OrderResponseDTO toResponseDTO(Order entity) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(entity.getId());
        dto.setPaymentStatus(entity.getPaymentStatusEnum());
        dto.setDeliveryStatus(entity.getDeliveryStatusEnum());
        dto.setInstant(entity.getInstant());
        
        if (entity.getClient() != null) {
            dto.setClient(clientMapper.toResponseDTO(entity.getClient()));
        }
        
        if (entity.getPayment() != null) {
            dto.setPayment(paymentMapper.toResponseDTO(entity.getPayment()));
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(Order entity, OrderRequestDTO dto) {
        entity.setPaymentStatusEnum(dto.getPaymentStatus());
        entity.setDeliveryStatusEnum(dto.getDeliveryStatus());
    }
}

