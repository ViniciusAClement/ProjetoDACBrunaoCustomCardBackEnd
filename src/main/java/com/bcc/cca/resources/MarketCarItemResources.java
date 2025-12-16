package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.MarketCarItemRequestDTO;
import com.bcc.cca.dto.response.MarketCarItemResponseDTO;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.entites.Product;
import com.bcc.cca.mapper.MarketCarItemMapper;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.ProductRepository;
import com.bcc.cca.services.MarketCarItemService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/marketcaritens")
public class MarketCarItemResources extends GenericResourceDTO<MarketCarItem, Long, MarketCarItemRequestDTO, MarketCarItemResponseDTO> {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MarketCarRepository marketCarRepository;
	
	@Autowired
    public MarketCarItemResources(MarketCarItemService service, MarketCarItemMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<MarketCarItemResponseDTO> insert(@Valid @RequestBody MarketCarItemRequestDTO dto) {
		MarketCarItem entity = mapper.toEntity(dto);
		
		if (dto.getProductId() != null) {
			Product product = productRepository.findById(dto.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.getProductId()));
			entity.setProduct(product);
		}
		
		if (dto.getMarketCarId() != null) {
			MarketCar marketCar = marketCarRepository.findById(dto.getMarketCarId())
				.orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado com ID: " + dto.getMarketCarId()));
			entity.setMarketcar(marketCar);
		}
		
		entity = service.create(entity);
		MarketCarItemResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/marketcaritens/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<MarketCarItemResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody MarketCarItemRequestDTO dto) {
		try {
			MarketCarItem entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			if (dto.getProductId() != null) {
				Product product = productRepository.findById(dto.getProductId())
					.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.getProductId()));
				entity.setProduct(product);
			}
			
			if (dto.getMarketCarId() != null) {
				MarketCar marketCar = marketCarRepository.findById(dto.getMarketCarId())
					.orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado com ID: " + dto.getMarketCarId()));
				entity.setMarketcar(marketCar);
			}
			
			entity = service.update(id, entity);
			MarketCarItemResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
