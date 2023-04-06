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

    @GetMapping("/all")
    public ResponseEntity<List<Patron>> getAllPatrons(){
        List<Patron> list = patronService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
