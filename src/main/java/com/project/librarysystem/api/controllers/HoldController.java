package com.project.librarysystem.api.controllers;

import com.project.librarysystem.api.dtos.HoldDTO;
import com.project.librarysystem.domain.services.HoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hold")
@RequiredArgsConstructor
public class HoldController {

    private final HoldService service;

    @PostMapping("/{patronId}/{bookId}")
    public ResponseEntity<Object> borrow(@PathVariable(value = "patronId") Long patronId,
                                         @PathVariable(value = "bookId") Long bookCopyId) {

        HoldDTO dto = service.borrow(patronId, bookCopyId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<Object> devolution(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoldDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
