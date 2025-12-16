package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.OrderRequestDTO;
import com.bcc.cca.dto.response.OrderResponseDTO;
import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.Order;
import com.bcc.cca.entites.Payment;
import com.bcc.cca.mapper.OrderMapper;
import com.bcc.cca.mapper.PaymentMapper;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.services.OrderService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/orders")
public class OrderResources extends GenericResourceDTO<Order, Long, OrderRequestDTO, OrderResponseDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
    public OrderResources(OrderService service, OrderMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<OrderResponseDTO> insert(@Valid @RequestBody OrderRequestDTO dto) {
		Order entity = mapper.toEntity(dto);
		
		if (dto.getClientId() != null) {
			Client client = clientRepository.findById(dto.getClientId())
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
			entity.setClient(client);
		}
		
		if (dto.getPayment() != null) {
			Payment payment = paymentMapper.toEntity(dto.getPayment());
			entity.setPayment(payment);
		}
		
		entity = service.create(entity);
		OrderResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/orders/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody OrderRequestDTO dto) {
		try {
			Order entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			if (dto.getClientId() != null) {
				Client client = clientRepository.findById(dto.getClientId())
					.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
				entity.setClient(client);
			}
			
			if (dto.getPayment() != null) {
				if (entity.getPayment() == null) {
					entity.setPayment(new Payment());
				}
				paymentMapper.updateEntityFromDTO(entity.getPayment(), dto.getPayment());
			}
			
			entity = service.update(id, entity);
			OrderResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
