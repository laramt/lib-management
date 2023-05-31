package com.project.librarysystem.domain.repositories;

import com.project.librarysystem.domain.models.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Long> {

    List<Hold> findByDueDateBeforeAndReturnedIsFalse(LocalDate currentDate);

}
