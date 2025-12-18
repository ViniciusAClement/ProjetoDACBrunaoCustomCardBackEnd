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

        double value = 0.0;

        for (MarketCarItem item : marketCar.getMarketCarItens()) {
            value += item.getPrice();
        }

        order.setPaymentValue(value);
        order.setClient(client);

        repository.save(order);

        marketCar.getMarketCarItens().clear();

        return mapper.toResponseDTO(order);
    }

}