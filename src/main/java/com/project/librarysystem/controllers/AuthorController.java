package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.AuthorDTO;
import com.project.librarysystem.services.AuthorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @PostMapping("/new-author")
    public ResponseEntity<AuthorDTO> insert(@RequestBody AuthorDTO dto) {
        dto = service.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<AuthorDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.findByName(name));
    }

}
