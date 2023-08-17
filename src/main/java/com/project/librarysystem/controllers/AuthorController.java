package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.request.AuthorRequest;
import com.project.librarysystem.dtos.response.AuthorResponse;
import com.project.librarysystem.services.AuthorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @PostMapping("/")
    public ResponseEntity<AuthorResponse> insert(@RequestBody AuthorRequest request) {
        return ResponseEntity.created(null).body(service.insert(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<AuthorResponse>> getByName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable("id") Long id,
                                            @RequestBody AuthorRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
