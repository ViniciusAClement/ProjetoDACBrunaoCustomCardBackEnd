package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.AdressRequestDTO;
import com.bcc.cca.dto.response.AdressResponseDTO;
import com.bcc.cca.entites.Adress;
import com.bcc.cca.entites.Client;
import com.bcc.cca.mapper.AdressMapper;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.services.AdressService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/addresses")
public class AdressResources extends GenericResourceDTO<Adress, Long, AdressRequestDTO, AdressResponseDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
    public AdressResources(AdressService service, AdressMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<AdressResponseDTO> insert(@Valid @RequestBody AdressRequestDTO dto) {
		Adress entity = mapper.toEntity(dto);
		
		if (dto.getClientId() != null) {
			Client client = clientRepository.findById(dto.getClientId())
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
			entity.setClient(client);
		}
		
		entity = service.create(entity);
		AdressResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/addresses/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<AdressResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody AdressRequestDTO dto) {
		try {
			Adress entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			if (dto.getClientId() != null) {
				Client client = clientRepository.findById(dto.getClientId())
					.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));
				entity.setClient(client);
			}
			
			entity = service.update(id, entity);
			AdressResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
