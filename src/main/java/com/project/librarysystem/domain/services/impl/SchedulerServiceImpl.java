package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.domain.models.Hold;
import com.project.librarysystem.domain.repositories.HoldRepository;
import com.project.librarysystem.domain.services.EmailService;
import com.project.librarysystem.domain.services.SchedulerService;
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

        List<Hold> holds = holdRepository.findByDueDateBeforeAndReturnedIsFalse(LocalDate.now());

        for (Hold hold : holds) {
            emailService.sendLateBook(hold);
        }

    }

}
