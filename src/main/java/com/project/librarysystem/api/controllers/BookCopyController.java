package com.project.librarysystem.api.controllers;

import com.project.librarysystem.api.dtos.BookCopyDTO;
import com.project.librarysystem.domain.services.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-copy")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyService service;

    @PostMapping("/new-book")
    public ResponseEntity<BookCopyDTO> insert(@RequestBody BookCopyDTO bookCopy) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.newBookCopy(bookCopy));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookCopyDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopyDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCopyDTO> update(@PathVariable Long id, @RequestBody BookCopyDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

}
