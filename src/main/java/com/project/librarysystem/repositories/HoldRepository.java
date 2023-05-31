package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Hold;

import java.time.LocalDate;
import java.util.List;

public interface HoldRepository extends JpaRepository<Hold, Long> {

    List<Hold> findByDueDateBeforeAndReturnedIsFalse(LocalDate currentDate);

}
