package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.CarService;
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

import com.bcc.cca.dto.request.CarRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarResource {

    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> findAll() {
        List<CarResponseDTO> cars = service.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> findById(@PathVariable Long id) {
        CarResponseDTO car = service.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> create(@Valid @RequestBody CarRequestDTO dto) {
        CarResponseDTO car = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CarRequestDTO dto) {
        CarResponseDTO car = service.update(id, dto);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








