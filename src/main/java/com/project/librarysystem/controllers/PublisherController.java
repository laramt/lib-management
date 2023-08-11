package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.request.PublisherRequest;
import com.project.librarysystem.dtos.response.PublisherResponse;
import com.project.librarysystem.services.PublisherService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService service;

    @PostMapping("/")
    public ResponseEntity<PublisherResponse> insert(@RequestBody PublisherRequest request){
        return ResponseEntity.created(null).body(service.insert(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<PublisherResponse>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }


  
}
