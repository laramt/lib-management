package com.project.librarysystem.services;

import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatronService {

    @Autowired
    PatronRepository patronRepository;

    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(UUID id){
        return patronRepository.findById(id);
    }

}
