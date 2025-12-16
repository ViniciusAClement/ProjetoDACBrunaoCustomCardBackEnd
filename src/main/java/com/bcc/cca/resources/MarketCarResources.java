package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.MarketCarRequestDTO;
import com.bcc.cca.dto.response.MarketCarResponseDTO;
import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.mapper.MarketCarMapper;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.services.MarketCarService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/marketcars")
public class MarketCarResources extends GenericResourceDTO<MarketCar, Long, MarketCarRequestDTO, MarketCarResponseDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
    public MarketCarResources(MarketCarService service, MarketCarMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<MarketCarResponseDTO> insert(@Valid @RequestBody MarketCarRequestDTO dto) {
		MarketCar entity = mapper.toEntity(dto);
		
		if (dto.getClientId() != null) {
			Client client = clientRepository.findById(dto.getClientId())
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
			entity.setClient(client);
		}
		
		entity = service.create(entity);
		MarketCarResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/marketcars/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<MarketCarResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody MarketCarRequestDTO dto) {
		try {
			MarketCar entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			if (dto.getClientId() != null) {
				Client client = clientRepository.findById(dto.getClientId())
					.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
				entity.setClient(client);
			}
			
			entity = service.update(id, entity);
			MarketCarResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
