package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
