package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.ProductService;
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

import com.bcc.cca.dto.request.ProductRequestDTO;
import com.bcc.cca.dto.response.ProductResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO product = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO product = service.update(id, dto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








