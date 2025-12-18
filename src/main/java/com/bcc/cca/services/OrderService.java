package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.EntityNotFoundException;
import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.PaymentRepository;
import com.bcc.cca.services.calculator.OrderCalculator;
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

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MarketCarRepository marketCarRepository;
    @Autowired
    private OrderCalculator orderCalculator;
    @Autowired
    private MarketCarService marketCarService;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected OrderRepository getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public OrderResponseDTO create(OrderRequestDTO dto){
        Order order = mapper.toEntity(dto);
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new EntityNotFoundException("Cliente Inexistente"));
        MarketCar marketCar = client.getMarketCar();

        order.setPaymentValue(orderCalculator.sumValue(marketCar));
        order.setClient(client);

        marketCarService.clear(marketCar);

        repository.save(order);
        return mapper.toResponseDTO(order);
    }

}