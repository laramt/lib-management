package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.PublisherDTO;
import com.project.librarysystem.services.PublisherService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService service;

    @GetMapping("/all")
    public ResponseEntity<List<PublisherDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
