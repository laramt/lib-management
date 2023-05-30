package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.api.dtos.EmailDTO;
import com.project.librarysystem.domain.mappers.EmailMapper;
import com.project.librarysystem.domain.models.Email;
import com.project.librarysystem.domain.models.Hold;
import com.project.librarysystem.domain.repositories.EmailRepository;
import com.project.librarysystem.domain.services.EmailService;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;
    private final EmailMapper mapper;
    private final  JavaMailSender emailSender;

    private static final String FROM_EMAIL = "email@email.com";
    private static final String BORROWED_SUBJECT = "Library Notification: Borrowed Book Details";


    @Override
    @Transactional
    public void sendBorrowedBook(Hold hold) {
        try {
            Email email = Email.builder()
                    .emailFrom(FROM_EMAIL)
                    .emailTo(hold.getPatron().getEmail())
                    .patron(hold.getPatron())
                    .subject(BORROWED_SUBJECT)
                    .text("Hello, " + hold.getPatron().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "IMPORTANT INFOS:\n"
                            + "Book: " + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor() + "\n"
                            + "Borrowed on: " + hold.getBorrowedDate() + "\n"
                            + "Due date: " + hold.getDueDate() + "\n"
                            + "Please note that if the book is returned after the due date, a fine will be imposed for every late day.\n"
                            + "We kindly request you to return the book on time to avoid any inconvenience.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.")
                    .build();

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getPatron().getEmail());
            helper.setSubject(BORROWED_SUBJECT);
            helper.setText(email.getText());

            emailSender.send(message);
            repository.save(email);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }

    }

    @Override
    public List<EmailDTO> findAll() {
        List<Email> emails = repository.findAll();
        return mapper.toBookDTOList(emails);
    }

    @Override
    public EmailDTO findById(Long id) {
        Email email = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Email with id " + id + " not found."));

        return mapper.toEmailDTO(email);
    }
}
