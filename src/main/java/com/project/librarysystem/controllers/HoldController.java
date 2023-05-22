package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.HoldDTO;
import com.project.librarysystem.services.HoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hold")
public class HoldController {

    @Autowired
    HoldService holdService;

    @PostMapping("/{patronId}/{bookId}")
    public ResponseEntity<Object> checkout(@PathVariable(value = "patronId") Long patronId,
                                           @PathVariable(value = "bookId") Long bookCopyId){
        HoldDTO hold = holdService.checkout(patronId, bookCopyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(hold);
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<Object> devolution(@PathVariable(value = "id") Long id){
        HoldDTO hd = holdService.findById(id);
        if (!Optional.of(hd).isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("HoldDTO with id " + id + " not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(holdService.devolution(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoldDTO>> getAllHoldDTO(){
        List<HoldDTO> list = holdService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHoldDTOById(@PathVariable Long id){
        HoldDTO hold = holdService.findById(id);
        if (!Optional.of(hold).isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("HoldDTO with id " + id + " not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(hold);
    }

}
