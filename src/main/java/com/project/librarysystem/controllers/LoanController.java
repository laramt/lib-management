package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.response.LoanResponse;
import com.project.librarysystem.services.LoanService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping(path = "/borrow")
    public ResponseEntity<LoanResponse> borrow(@RequestParam(required = true, name = "patronId") Long patronId,
                                         @RequestParam(required = true, name = "bookCopyId") Long bookCopyId) {

        LoanResponse dto = service.borrow(patronId, bookCopyId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<LoanResponse> devolution(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
