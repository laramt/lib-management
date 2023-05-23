package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.PatronDTO;
import com.project.librarysystem.services.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patron")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService service;

    @PostMapping("/register")
    public ResponseEntity<PatronDTO> register(@RequestBody PatronDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerNewPatron(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatronDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> update(@PathVariable("id") Long id, @RequestBody PatronDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

}
