package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.PaymentService;
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

import com.bcc.cca.dto.request.PaymentRequestDTO;
import com.bcc.cca.dto.response.PaymentResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findAll() {
        List<PaymentResponseDTO> payments = service.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable Long id) {
        PaymentResponseDTO payment = service.findById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO dto) {
        PaymentResponseDTO payment = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO dto) {
        PaymentResponseDTO payment = service.update(id, dto);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








