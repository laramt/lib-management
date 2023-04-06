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

    public Patron registerNewPatron(Patron patron){

        // email validation
        String email = patron.getEmail();
        if (email == null || email.isEmpty()){
            throw new RuntimeException("Email cannot be null or empty.");
        }

        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            throw new RuntimeException("Invalid email.");
        }

        //phone number validation
        String phone = patron.getPhoneNumber();
        if (phone == null || phone.isEmpty()){
            throw new RuntimeException("Phone number cannot be null or empty");
        }

        Pattern patternPhone = Pattern.compile("^\\d{13}$");
        Matcher matcherPhone = patternPhone.matcher(phone);
        if (!matcherPhone.matches()) {
            throw new RuntimeException("Invalid phone number.");
        }

        //birthdate validation
        LocalDate birthdate = patron.getBirthDate();
        if (birthdate == null || birthdate.toString().isEmpty()){
            throw new RuntimeException("Birthdate cannot be null or empty");
        }

        LocalDate today = LocalDate.now();
        LocalDate sixteenYearsAgo = today.minusYears(16);
        if (patron.getBirthDate().isBefore(sixteenYearsAgo)){
            throw new RuntimeException("Patron must be at least 16 years old.");
        }

        return patronRepository.save(patron);
    }

    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(UUID id){
        return patronRepository.findById(id);
    }

}
