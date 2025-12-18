package com.bcc.cca.resources;

import java.util.List;

import com.bcc.cca.services.CardInfoService;
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

import com.bcc.cca.dto.request.CardInfoRequestDTO;
import com.bcc.cca.dto.response.CardInfoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cardinfos")
public class CardInfoResource {

    @Autowired
    private CardInfoService service;

    @GetMapping
    public ResponseEntity<List<CardInfoResponseDTO>> findAll() {
        List<CardInfoResponseDTO> cardInfos = service.findAll();
        return ResponseEntity.ok(cardInfos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardInfoResponseDTO> findById(@PathVariable Long id) {
        CardInfoResponseDTO cardInfo = service.findById(id);
        return ResponseEntity.ok(cardInfo);
    }

    @PostMapping
    public ResponseEntity<CardInfoResponseDTO> create(@Valid @RequestBody CardInfoRequestDTO dto) {
        CardInfoResponseDTO cardInfo = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardInfoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CardInfoRequestDTO dto) {
        CardInfoResponseDTO cardInfo = service.update(id, dto);
        return ResponseEntity.ok(cardInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}








