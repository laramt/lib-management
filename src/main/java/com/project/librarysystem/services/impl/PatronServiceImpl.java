package com.project.librarysystem.services.impl;

import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.PatronRepository;
import com.project.librarysystem.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    PatronRepository patronRepository;
    private static final int MINIMUM_AGE = 16;

    @Transactional
    public Patron registerNewPatron(Patron patron){
        validatePatronData(patron.getEmail(), patron.getPhoneNumber(), patron.getBirthDate());
        return patronRepository.save(patron);
    }

    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    public Patron findById(Long id){
        Optional<Patron> pt = patronRepository.findById(id);
        Patron patron = pt.orElseThrow(() -> new ResourceNotFoundException("Patron with id " + id + " not found."));
        return patron;
    }

    @Transactional
    public Patron update(Long id, Patron patron){
        Patron pt = findById(id);
        validatePatronData(patron.getEmail(), patron.getPhoneNumber(), patron.getBirthDate());

        pt.setName(patron.getName());
        pt.setEmail(patron.getEmail());
        pt.setPhoneNumber(patron.getPhoneNumber());
        pt.setBirthDate(patron.getBirthDate());

        return patronRepository.save(pt);
    }

    private void validatePatronData(String email, String phoneNumber, LocalDate birthDate){

        if (email == null || email.isEmpty()){
            throw new InvalidInputException("Email cannot be null or empty.");
        }

        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            throw new InvalidInputException("Invalid email.");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()){
            throw new InvalidInputException("Phone number cannot be null or empty");
        }

        Pattern patternPhone = Pattern.compile("^\\d{13}$");
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        if (!matcherPhone.matches()) {
            throw new InvalidInputException("Invalid phone number.");
        }

        if (birthDate == null || birthDate.toString().isEmpty()){
            throw new InvalidInputException("Birthdate cannot be null or empty");
        }

        LocalDate today = LocalDate.now();
        LocalDate minimumAgeDate = today.minusYears(MINIMUM_AGE);
        if (birthDate.isAfter(minimumAgeDate)){
            throw new InvalidInputException("Patron must be at least " + MINIMUM_AGE + " years old.");
        }

    }

}
