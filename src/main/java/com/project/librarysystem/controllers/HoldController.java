package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.HoldDTO;
import com.project.librarysystem.services.HoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hold")
@RequiredArgsConstructor
public class HoldController {

    private final HoldService service;

    @PostMapping("/{patronId}/{bookId}")
    public ResponseEntity<Object> checkout(@PathVariable(value = "patronId") Long patronId,
                                           @PathVariable(value = "bookId") Long bookCopyId) {
        HoldDTO hold = service.checkout(patronId, bookCopyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(hold);
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<Object> devolution(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoldDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

}
