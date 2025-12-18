package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.CategoryService;
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

import com.bcc.cca.dto.request.CategoryRequestDTO;
import com.bcc.cca.dto.response.CategoryResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        CategoryResponseDTO category = service.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO category = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO category = service.update(id, dto);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








