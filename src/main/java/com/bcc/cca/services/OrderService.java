package com.bcc.cca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.Order;
import com.bcc.cca.entites.Payment;
import com.bcc.cca.entites.enumeration.PaymentMethod;
import com.bcc.cca.entites.enumeration.PaymentStatus;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.OrderRepository;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService extends GenericService<Order, Long>{

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private MarketCarRepository marketCarRepository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	
	@Override
	@Transactional
	public Order create(Order obj) {
		Client client;
		
		if (obj.getClient() != null && obj.getClient().getId() != null) {
			client = clientRepository.findById(obj.getClient().getId())
				.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Client não encontrado com ID: " + obj.getClient().getId()));
		} else {
			throw new IllegalArgumentException("Client é obrigatório para criar um pedido");
		}
		
		obj.setClient(client);
		
		Optional<MarketCar> marketCarOpt = marketCarRepository.findByClientWithItems(client);
		
		if (marketCarOpt.isEmpty() || marketCarOpt.get().getMarketCarItens().isEmpty()) {
			throw new IllegalArgumentException("Carrinho vazio ou não encontrado para o cliente");
		}
		
		MarketCar marketCar = marketCarOpt.get();
		
		double totalAmount = marketCar.getMarketCarItens().stream()
			.mapToDouble(item -> item.getPrice() * item.getQuantity())
			.sum();
		
		Payment payment = new Payment();
		payment.setInstant(java.time.Instant.now());
		
		if (obj.getPayment() != null && obj.getPayment().getPaymentMethod() != null) {
			payment.setPaymentMethod(obj.getPayment().getPaymentMethod());
		} else {
			payment.setPaymentMethod(PaymentMethod.CARD);
		}
		
		payment.setAmount(totalAmount);
		obj.setPayment(payment);
		
		if (obj.getPaymentStatus() == null) {
			obj.setPaymentStatus(PaymentStatus.UNPAID);
		}
		
		if (obj.getDeliveryStatus() == null) {
			obj.setDeliveryStatus(com.bcc.cca.entites.enumeration.DeliveryStatus.PROCESSING);
		}
		
		return repository.save(obj);
	}

    @Override
    protected void updateData(Order entity, Order newObj) {
        if (newObj.getClient() != null && newObj.getClient().getId() != null) {
        	Client client = clientRepository.findById(newObj.getClient().getId())
        		.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Client não encontrado com ID: " + newObj.getClient().getId()));
        	entity.setClient(client);
        }
        
        entity.setDeliveryStatus(newObj.getDeliveryStatus());
        entity.setInstant(newObj.getInstant());
        entity.setPaymentStatus(newObj.getPaymentStatus());
        
        if (newObj.getPayment() != null) {
        	if (entity.getPayment() == null) {
        		entity.setPayment(new Payment());
        	}
        	if (newObj.getPayment().getPaymentMethod() != null) {
        		entity.getPayment().setPaymentMethod(newObj.getPayment().getPaymentMethod());
        	}
        	if (newObj.getPayment().getAmount() != null) {
        		entity.getPayment().setAmount(newObj.getPayment().getAmount());
        	}
        }
    }
}
