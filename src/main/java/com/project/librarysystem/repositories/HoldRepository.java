package com.project.librarysystem.repositories;

import com.project.librarysystem.models.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HoldRepository extends JpaRepository<Hold, UUID> {
}
