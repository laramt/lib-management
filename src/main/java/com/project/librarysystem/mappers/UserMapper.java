package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.UserRequest;
import com.project.librarysystem.dtos.response.UserResponse;
import com.project.librarysystem.models.User;

import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toUser(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserResponse toUserResponse(User entity) {
        return mapper.map(entity, UserResponse.class);
    }

    public List<UserResponse> toUserResponseList(List<User> list) {
        return list.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }


    public User updateUserFromRequest(UserRequest request, User entity) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(request, entity);
        return entity;
    }
}
