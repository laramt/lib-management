package com.project.librarysystem.api.controllers;

import com.project.librarysystem.api.dtos.PatronDTO;
import com.project.librarysystem.domain.services.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PatronDTO> register(@RequestBody PatronDTO dto) {
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
