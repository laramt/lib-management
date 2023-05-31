package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.EmailDTO;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.EmailMapper;
import com.project.librarysystem.models.Email;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.repositories.EmailRepository;
import com.project.librarysystem.services.EmailService;

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


    @Override
    @Transactional
    public void sendBorrowedBook(Hold hold) {
        try {
            Email email = Email.builder()
                    .emailFrom(FROM_EMAIL)
                    .emailTo(hold.getPatron().getEmail())
                    .patron(hold.getPatron())
                    .subject("Borrowed Book Details")
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
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());

            emailSender.send(message);
            repository.save(email);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }

    }

    @Override
    public void sendLateBook(Hold hold) {
        try {
            Email email = Email.builder()
                    .emailFrom(FROM_EMAIL)
                    .emailTo(hold.getPatron().getEmail())
                    .patron(hold.getPatron())
                    .subject("Book Not Return On Due Day.")
                    .text("Hello, " + hold.getPatron().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "Please note that the volume of the "
                            + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor()
                            + " borrowed on " + hold.getBorrowedDate()
                            + " was due on " + hold.getDueDate() + ". \n"
                            + "We kindly request you to return the book to the Library.\n"
                            + "You will need to pay a fee regarding the late devolution.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.")
                    .build();

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getPatron().getEmail());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());

            emailSender.send(message);
            repository.save(email);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    @Override
    public void sendDueDateTomorrow(Hold hold) {
        try {
            Email email = Email.builder()
                    .emailFrom(FROM_EMAIL)
                    .emailTo(hold.getPatron().getEmail())
                    .patron(hold.getPatron())
                    .subject("Book Due Tomorrow.")
                    .text("Hello, " + hold.getPatron().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "Please note that the volume of the "
                            + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor()
                            + " borrowed on " + hold.getBorrowedDate() + " is due tomorrow.\n"
                            + "We kindly request you to return the book to the Library till the end of the day or you will be charged a fine.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.")
                    .build();

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getPatron().getEmail());
            helper.setSubject(email.getSubject());
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
