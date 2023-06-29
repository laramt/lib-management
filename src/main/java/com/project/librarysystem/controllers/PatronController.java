package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.PatronDTO;
import com.project.librarysystem.services.PatronService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patron")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService service;

    @PostMapping("/register")
    public ResponseEntity<PatronDTO> register(@RequestBody @Valid PatronDTO dto) {
        dto = service.registerNewPatron(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatronDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> update(@PathVariable("id") Long id,
                                            @RequestBody PatronDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

}
