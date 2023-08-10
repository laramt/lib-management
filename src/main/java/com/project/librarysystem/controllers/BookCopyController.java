package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.request.BookCopyRequest;
import com.project.librarysystem.dtos.response.BookCopyResponse;
import com.project.librarysystem.services.BookCopyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyService service;

    @PostMapping("/")
    public ResponseEntity<BookCopyResponse> insert(@RequestBody @Valid BookCopyRequest request) {
        return ResponseEntity.created(null).body(service.newBookCopy(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<BookCopyResponse>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCopyResponse> update(@PathVariable Long id,
                                              @RequestBody BookCopyRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

}
