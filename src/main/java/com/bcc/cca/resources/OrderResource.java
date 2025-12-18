package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.OrderService;
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

import com.bcc.cca.dto.request.OrderRequestDTO;
import com.bcc.cca.dto.response.OrderResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<OrderResponseDTO> orders = service.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        OrderResponseDTO order = service.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO dto) {
        OrderResponseDTO order = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO dto) {
        OrderResponseDTO order = service.update(id, dto);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








