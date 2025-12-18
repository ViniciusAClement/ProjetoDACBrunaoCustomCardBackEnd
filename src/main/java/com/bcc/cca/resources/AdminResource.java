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

import com.bcc.cca.dto.request.AdminRequestDTO;
import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.services.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminResource {

    @Autowired
    private AdminService service;

    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> findAll() {
        List<AdminResponseDTO> admins = service.findAll();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> findById(@PathVariable Long id) {
        AdminResponseDTO admin = service.findById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public ResponseEntity<AdminResponseDTO> create(@Valid @RequestBody AdminRequestDTO dto) {
        AdminResponseDTO admin = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AdminRequestDTO dto) {
        AdminResponseDTO admin = service.update(id, dto);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








