package com.project.librarysystem.services.impl;

import com.project.librarysystem.models.Hold;
import com.project.librarysystem.repositories.HoldRepository;
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
    private final HoldRepository holdRepository;

    @Override
    @Scheduled(cron = "0 0 10 ? * MON") // every monday at 10 AM
    public void lateDevolutionEmail() {

        List<Hold> holds = holdRepository
            .findByDueDateBeforeAndReturnedIsFalse(LocalDate.now());

        holds.stream()
            .forEach(hold -> emailService.sendLateBook(hold));
    }

    @Override
    @Scheduled(cron = "0 0 20 * * *") // every day at 08 PM
    public void dueDateTomorrowEmail() {
        
        List<Hold> holds = holdRepository
            .findByDueDate(LocalDate.now().plusDays(1));
        
        holds.stream()
            .forEach(hold -> emailService.sendDueDateTomorrow(hold));
    }

}
