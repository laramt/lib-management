package com.project.librarysystem.services.impl;

import com.project.librarysystem.models.Loan;
import com.project.librarysystem.repositories.LoanRepository;
import com.project.librarysystem.services.EmailService;
import com.project.librarysystem.services.SchedulerService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final EmailService emailService;
    private final LoanRepository loanRepository;

    @Override
    @Scheduled(cron = "0 0 10 ? * MON") // every monday at 10 AM
    public void lateDevolutionEmail() {

        List<Loan> loans = loanRepository
            .findByDueDateBeforeAndReturnedIsFalse(LocalDate.now());

        loans.stream()
            .forEach(hold -> emailService.sendLateBook(hold));
    }

    @Override
    @Scheduled(cron = "0 0 20 * * *") // every day at 08 PM
    public void dueDateTomorrowEmail() {
        
        List<Loan> loans = loanRepository
            .findByDueDate(LocalDate.now().plusDays(1));
        
        loans.stream()
            .forEach(hold -> emailService.sendDueDateTomorrow(hold));
    }

}
