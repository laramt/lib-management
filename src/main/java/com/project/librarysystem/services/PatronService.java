package com.project.librarysystem.services;

import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private static final int MINIMUM_AGE = 16;

    @Transactional
    public Patron registerNewPatron(Patron patron){
        validateEmail(patron.getEmail());
        validatePhoneNumber(patron.getPhoneNumber());
        validateBirthDate(patron.getBirthDate());

        return patronRepository.save(patron);
    }

    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(Long id){
        return patronRepository.findById(id);
    }

    private void validateEmail(String email) {

        if (email == null || email.isEmpty()){
            throw new RuntimeException("Email cannot be null or empty.");
        }

        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            throw new RuntimeException("Invalid email.");
        }

    }

    private void validatePhoneNumber(String phoneNumber) {

        if (phoneNumber == null || phoneNumber.isEmpty()){
            throw new RuntimeException("Phone number cannot be null or empty");
        }

        Pattern patternPhone = Pattern.compile("^\\d{13}$");
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        if (!matcherPhone.matches()) {
            throw new RuntimeException("Invalid phone number.");
        }

    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null || birthDate.toString().isEmpty()){
            throw new RuntimeException("Birthdate cannot be null or empty");
        }

        LocalDate today = LocalDate.now();
        LocalDate minimumAgeDate = today.minusYears(MINIMUM_AGE);
        if (birthDate.isAfter(minimumAgeDate)){
            throw new RuntimeException("Patron must be at least " + MINIMUM_AGE + " years old.");
        }
    }

}
