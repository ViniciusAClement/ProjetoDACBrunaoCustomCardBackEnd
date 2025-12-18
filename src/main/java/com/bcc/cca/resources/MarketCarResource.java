package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.MarketCarService;
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

import com.bcc.cca.dto.request.MarketCarRequestDTO;
import com.bcc.cca.dto.response.MarketCarResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/marketcars")
public class MarketCarResource {

    @Autowired
    private MarketCarService service;

    @GetMapping
    public ResponseEntity<List<MarketCarResponseDTO>> findAll() {
        List<MarketCarResponseDTO> marketCars = service.findAll();
        return ResponseEntity.ok(marketCars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketCarResponseDTO> findById(@PathVariable Long id) {
        MarketCarResponseDTO marketCar = service.findById(id);
        return ResponseEntity.ok(marketCar);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<MarketCarResponseDTO> findByClientId(@PathVariable Long clientId) {
        MarketCarResponseDTO marketCar = service.findByClientId(clientId);
        return ResponseEntity.ok(marketCar);
    }

    @PostMapping
    public ResponseEntity<MarketCarResponseDTO> create(@Valid @RequestBody MarketCarRequestDTO dto) {
        MarketCarResponseDTO marketCar = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(marketCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketCarResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MarketCarRequestDTO dto) {
        MarketCarResponseDTO marketCar = service.update(id, dto);
        return ResponseEntity.ok(marketCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








