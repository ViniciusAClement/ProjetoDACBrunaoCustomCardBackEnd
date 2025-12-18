package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.CarBrandRequestDTO;
import com.bcc.cca.dto.response.CarBrandResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carbrands")
public class CarBrandResource {

    @Autowired
    private CarBrandService service;

    @GetMapping
    public ResponseEntity<List<CarBrandResponseDTO>> findAll() {
        List<CarBrandResponseDTO> carBrands = service.findAll();
        return ResponseEntity.ok(carBrands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarBrandResponseDTO> findById(@PathVariable Long id) {
        CarBrandResponseDTO carBrand = service.findById(id);
        return ResponseEntity.ok(carBrand);
    }

    @PostMapping
    public ResponseEntity<CarBrandResponseDTO> create(@Valid @RequestBody CarBrandRequestDTO dto) {
        CarBrandResponseDTO carBrand = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(carBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CarBrandRequestDTO dto) {
        CarBrandResponseDTO carBrand = service.update(id, dto);
        return ResponseEntity.ok(carBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








