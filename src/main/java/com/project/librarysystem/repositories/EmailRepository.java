package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.librarysystem.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
