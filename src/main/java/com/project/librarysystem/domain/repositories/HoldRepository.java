package com.project.librarysystem.domain.repositories;

import com.project.librarysystem.domain.models.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Long> {
}
