package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.CarRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;
import com.bcc.cca.entites.Car;
import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.mapper.CarMapper;
import com.bcc.cca.repositories.CarBrandRepository;
import com.bcc.cca.services.CarService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/cars")
public class CarResources extends GenericResourceDTO<Car, Long, CarRequestDTO, CarResponseDTO> {
	
	@Autowired
	private CarBrandRepository carBrandRepository;
	
	@Autowired
    public CarResources(CarService service, CarMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<CarResponseDTO> insert(@Valid @RequestBody CarRequestDTO dto) {
		Car entity = mapper.toEntity(dto);
		
		if (dto.getCarBrandId() != null) {
			CarBrand carBrand = carBrandRepository.findById(dto.getCarBrandId())
				.orElseThrow(() -> new EntityNotFoundException("Marca de carro não encontrada com ID: " + dto.getCarBrandId()));
			entity.setCarBrand(carBrand);
		}
		
		entity = service.create(entity);
		CarResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/cars/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<CarResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody CarRequestDTO dto) {
		try {
			Car entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			if (dto.getCarBrandId() != null) {
				CarBrand carBrand = carBrandRepository.findById(dto.getCarBrandId())
					.orElseThrow(() -> new EntityNotFoundException("Marca de carro não encontrada com ID: " + dto.getCarBrandId()));
				entity.setCarBrand(carBrand);
			}
			
			entity = service.update(id, entity);
			CarResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
