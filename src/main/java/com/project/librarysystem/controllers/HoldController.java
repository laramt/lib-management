package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.HoldDTO;
import com.project.librarysystem.services.HoldService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hold")
@RequiredArgsConstructor
public class HoldController {

    private final HoldService service;

    @PostMapping(path = "/borrow")
    public ResponseEntity<HoldDTO> borrow(@RequestParam(required = true, name = "patronId") Long patronId,
                                         @RequestParam(required = true, name = "bookCopyId") Long bookCopyId) {

        HoldDTO dto = service.borrow(patronId, bookCopyId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<HoldDTO> devolution(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoldDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoldDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
