package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.PatronDTO;
import com.project.librarysystem.services.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/patron")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @PostMapping("/register")
    public ResponseEntity<PatronDTO> registerNewPatronDTO(@RequestBody PatronDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patronService.registerNewPatron(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatronDTO>> getAllPatronDTOs() {
        List<PatronDTO> list = patronService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatronDTOById(@PathVariable("id") Long id) {
        PatronDTO patron = patronService.findById(id);
        if (!Optional.of(patron).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PatronDTO does not exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body(patron);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody PatronDTO patron) {
        PatronDTO pt = patronService.findById(id);
        if (!Optional.of(pt).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PatronDTO does not exist");
        }
        PatronDTO updatedPatronDTO = patronService.update(id, patron);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatronDTO);

    }

}
