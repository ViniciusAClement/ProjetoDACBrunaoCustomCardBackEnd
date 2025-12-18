package com.bcc.cca.resources;

import java.util.List;

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

import com.bcc.cca.dto.request.AdressRequestDTO;
import com.bcc.cca.dto.response.AdressResponseDTO;
import com.bcc.cca.services.AdressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adresses")
public class AdressResource {

    @Autowired
    private AdressService service;

    @GetMapping
    public ResponseEntity<List<AdressResponseDTO>> findAll() {
        List<AdressResponseDTO> adresses = service.findAll();
        return ResponseEntity.ok(adresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdressResponseDTO> findById(@PathVariable Long id) {
        AdressResponseDTO adress = service.findById(id);
        return ResponseEntity.ok(adress);
    }

    @PostMapping
    public ResponseEntity<AdressResponseDTO> create(@Valid @RequestBody AdressRequestDTO dto) {
        AdressResponseDTO adress = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(adress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdressResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AdressRequestDTO dto) {
        AdressResponseDTO adress = service.update(id, dto);
        return ResponseEntity.ok(adress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








