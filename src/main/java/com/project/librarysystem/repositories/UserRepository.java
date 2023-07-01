package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
