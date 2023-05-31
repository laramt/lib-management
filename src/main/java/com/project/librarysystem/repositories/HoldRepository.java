package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.librarysystem.models.Hold;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Long> {

    List<Hold> findByDueDateBeforeAndReturnedIsFalse(LocalDate currentDate);

}
