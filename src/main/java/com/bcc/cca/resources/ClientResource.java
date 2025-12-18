package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.ClientService;
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

import com.bcc.cca.dto.request.ClientRequestDTO;
import com.bcc.cca.dto.response.ClientResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        List<ClientResponseDTO> clients = service.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO client = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO client = service.update(id, dto);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








