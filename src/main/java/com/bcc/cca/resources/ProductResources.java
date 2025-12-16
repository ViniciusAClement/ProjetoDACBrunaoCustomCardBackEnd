package com.bcc.cca.resources;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.ProductRequestDTO;
import com.bcc.cca.dto.response.ProductResponseDTO;
import com.bcc.cca.entites.Car;
import com.bcc.cca.entites.Category;
import com.bcc.cca.entites.Product;
import com.bcc.cca.mapper.ProductMapper;
import com.bcc.cca.repositories.CarRepository;
import com.bcc.cca.repositories.CategoryRepository;
import com.bcc.cca.services.ProductService;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductResources extends GenericResourceDTO<Product, Long, ProductRequestDTO, ProductResponseDTO> {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
    public ProductResources(ProductService service, ProductMapper mapper) {
        super(service, mapper);
    }
	
	@Override
	@PostMapping
	public ResponseEntity<ProductResponseDTO> insert(@Valid @RequestBody ProductRequestDTO dto) {
		Product entity = mapper.toEntity(dto);
		
		// Processar relacionamentos com categorias - criar objetos com apenas IDs
		// O ProductService.create() irá buscar e processar os relacionamentos
		if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
			Set<Category> categories = new HashSet<>();
			for (Long categoryId : dto.getCategoryIds()) {
				Category category = new Category();
				category.setId(categoryId);
				categories.add(category);
			}
			entity.getCategories().addAll(categories);
		}
		
		// Processar relacionamentos com carros - criar objetos com apenas IDs
		// O ProductService.create() irá buscar e processar os relacionamentos
		if (dto.getCarIds() != null && !dto.getCarIds().isEmpty()) {
			Set<Car> cars = new HashSet<>();
			for (Long carId : dto.getCarIds()) {
				Car car = new Car();
				car.setId(carId);
				cars.add(car);
			}
			entity.getCars().addAll(cars);
		}
		
		entity = service.create(entity);
		ProductResponseDTO responseDTO = mapper.toResponseDTO(entity);
		
		return ResponseEntity.created(
			java.net.URI.create("/products/" + entity.getId())
		).body(responseDTO);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ProductRequestDTO dto) {
		try {
			Product entity = service.findById(id);
			mapper.updateEntityFromDTO(entity, dto);
			
			// Processar relacionamentos com categorias
			if (dto.getCategoryIds() != null) {
				// Remover relacionamentos antigos
				for (Category oldCategory : entity.getCategories()) {
					oldCategory.getProducts().remove(entity);
				}
				entity.getCategories().clear();
				
				// Adicionar novos relacionamentos
				for (Long categoryId : dto.getCategoryIds()) {
					Category category = categoryRepository.findById(categoryId)
						.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + categoryId));
					entity.getCategories().add(category);
					category.getProducts().add(entity);
				}
			}
			
			// Processar relacionamentos com carros
			if (dto.getCarIds() != null) {
				// Remover relacionamentos antigos
				for (Car oldCar : entity.getCars()) {
					oldCar.getProducts().remove(entity);
				}
				entity.getCars().clear();
				
				// Adicionar novos relacionamentos
				for (Long carId : dto.getCarIds()) {
					Car car = carRepository.findById(carId)
						.orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com ID: " + carId));
					entity.getCars().add(car);
					car.getProducts().add(entity);
				}
			}
			
			entity = service.update(id, entity);
			ProductResponseDTO responseDTO = mapper.toResponseDTO(entity);
			return ResponseEntity.ok().body(responseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
