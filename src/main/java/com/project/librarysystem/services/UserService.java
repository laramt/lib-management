package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.request.UserRequest;
import com.project.librarysystem.dtos.response.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest user);
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    UserResponse update(Long id, UserRequest user);

}
