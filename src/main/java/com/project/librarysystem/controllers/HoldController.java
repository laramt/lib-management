package com.project.librarysystem.controllers;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.services.HoldService;
import com.project.librarysystem.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/hold")
public class HoldController {

    @Autowired
    HoldService holdService;
    @Autowired
    PatronService patronService;

    @PostMapping("/{patronId}/{bookId}")
    public ResponseEntity<Object> checkout(@PathVariable(value = "patronId") UUID patronId,
                                           @PathVariable(value = "bookId") UUID bookId){
        return ResponseEntity.status(HttpStatus.CREATED).body(holdService.checkout(patronId, bookId));
    }

    @PutMapping("devolution/{id}")
    public ResponseEntity<Object> devolution(@PathVariable(value = "id") UUID id){

        Optional<Hold> hd = holdService.findById(id);
        if (!hd.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hold does not exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body(holdService.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hold>> getAllHold(){
        List<Hold> list = holdService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
