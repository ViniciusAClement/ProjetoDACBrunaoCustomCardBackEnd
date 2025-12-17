package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.OrderRequestDTO;
import com.bcc.cca.dto.response.OrderResponseDTO;
import com.bcc.cca.entites.Order;
import com.bcc.cca.mapper.OrderMapper;
import com.bcc.cca.repositories.OrderRepository;

@Service
public class OrderService extends GenericServices<Order,OrderRequestDTO,OrderResponseDTO,OrderMapper>{

    private final OrderRepository repository;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected OrderRepository getRepository() {
        return repository;
    }
}