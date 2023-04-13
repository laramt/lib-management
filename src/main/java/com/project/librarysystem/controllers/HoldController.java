package com.project.librarysystem.controllers;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.services.HoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hold")
public class HoldController {

    @Autowired
    HoldService holdService;

    @GetMapping("/all")
    public ResponseEntity<List<Hold>> getAllHold(){
        List<Hold> list = holdService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
