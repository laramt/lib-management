package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByDueDateBeforeAndReturnedIsFalse(LocalDate currentDate);

    List<Loan> findByDueDate(LocalDate tomorrow);

}
