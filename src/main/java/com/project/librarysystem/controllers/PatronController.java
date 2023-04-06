package com.project.librarysystem.controllers;

import com.project.librarysystem.models.Patron;
import com.project.librarysystem.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/patron")
public class PatronController {

    @Autowired
    PatronService patronService;

    @PostMapping("/register")
    public ResponseEntity<Patron> registerNewPatron(@RequestBody Patron patron){

        return ResponseEntity.status(HttpStatus.CREATED).body(patronService.registerNewPatron(patron));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patron>> getAllPatrons(){
        List<Patron> list = patronService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getPatronById(@PathVariable("id") UUID id){
        Optional<Patron> patron = patronService.findById(id);
        if (!patron.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patron does not exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body(patron);

    }

}
