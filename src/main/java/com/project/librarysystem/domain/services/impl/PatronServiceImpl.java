package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.domain.services.PatronService;
import com.project.librarysystem.api.dtos.PatronDTO;
import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.domain.mappers.PatronMapper;
import com.project.librarysystem.domain.models.Patron;
import com.project.librarysystem.domain.repositories.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    private final PatronMapper mapper;
    private static final int MINIMUM_AGE = 16;

    @Override
    @Transactional
    public PatronDTO registerNewPatron(PatronDTO dto) {
        validatePatronData(dto.getEmail(), dto.getPhoneNumber(), dto.getBirthDate());
        patronRepository.save(mapper.toPatron(dto));
        return dto;

    }

    @Override
    public List<PatronDTO> findAll() {
        return mapper.toPatronDTOList(patronRepository.findAll());
    }

    @Override
    public PatronDTO findById(Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron with id " + id + " not found."));

        return mapper.toPatronDTO(patron);

    }

    @Override
    @Transactional
    public PatronDTO update(Long id, PatronDTO dto) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron with id " + id + " not found."));

        validatePatronData(dto.getEmail(), dto.getPhoneNumber(), dto.getBirthDate());

        patron.setName(dto.getName());
        patron.setEmail(dto.getEmail());
        patron.setPhoneNumber(dto.getPhoneNumber());
        patron.setBirthDate(dto.getBirthDate());

        patronRepository.save(patron);
        return mapper.toPatronDTO(patron);
    }

    private void validatePatronData(String email, String phoneNumber, LocalDate birthDate) {

        if (email == null || email.isEmpty()) {
            throw new InvalidInputException("Email cannot be null or empty.");
        }

        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            throw new InvalidInputException("Invalid email.");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InvalidInputException("Phone number cannot be null or empty");
        }

        Pattern patternPhone = Pattern.compile("^\\d{13}$");
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        if (!matcherPhone.matches()) {
            throw new InvalidInputException("Invalid phone number.");
        }

        if (birthDate == null || birthDate.toString().isEmpty()) {
            throw new InvalidInputException("Birthdate cannot be null or empty");
        }

        LocalDate today = LocalDate.now();
        LocalDate minimumAgeDate = today.minusYears(MINIMUM_AGE);
        if (birthDate.isAfter(minimumAgeDate)) {
            throw new InvalidInputException("Patron must be at least " + MINIMUM_AGE + " years old.");
        }

    }

}
