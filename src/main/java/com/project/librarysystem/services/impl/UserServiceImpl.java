package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.request.UserRequest;
import com.project.librarysystem.dtos.response.UserResponse;
import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.UserMapper;
import com.project.librarysystem.models.User;
import com.project.librarysystem.repositories.UserRepository;
import com.project.librarysystem.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final int MINIMUM_AGE = 16;

    @Override
    @Transactional
    public UserResponse registerUser(UserRequest dto) {
        validatePatronData(dto.getEmail(), dto.getPhoneNumber(), dto.getBirthDate());
        User user = repository.save(mapper.toUser(dto));
        return mapper.toUserResponse(user);

    }

    @Override
    public List<UserResponse> getAll() {
        return mapper.toUserResponseList(repository.findAll());
    }

    @Override
    public UserResponse getById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));

        return mapper.toUserResponse(user);

    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));

        validatePatronData(dto.getEmail(), dto.getPhoneNumber(), dto.getBirthDate());

        user = mapper.updateUserFromRequest(dto, user);
        repository.save(user);
        return mapper.toUserResponse(user);
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
