package com.project.librarysystem.services.impl;

import com.project.librarysystem.models.Hold;
import com.project.librarysystem.services.EmailService;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
   JavaMailSender emailSender;

    private static final String FROM_EMAIL = "email@email.com";


    @Override
    @Transactional
    public void sendBorrowedBook(Hold hold) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getUser().getEmail());
            helper.setSubject("Borrowed Book Details");
            helper.setText("Hello, " + hold.getUser().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "IMPORTANT INFOS:\n"
                            + "Book: " + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor().getName() + "\n"
                            + "Borrowed on: " + hold.getBorrowedDate() + "\n"
                            + "Due date: " + hold.getDueDate() + "\n"
                            + "Please note that if the book is returned after the due date, a fine will be imposed for every late day.\n"
                            + "We kindly request you to return the book on time to avoid any inconvenience.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.");

            emailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }

    }

    @Override
    public void sendLateBook(Hold hold) {
        try {
            
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getUser().getEmail());
            helper.setSubject("Book Not Return On Due Day.");
            helper.setText("Hello, " + hold.getUser().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "Please note that the volume of the "
                            + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor()
                            + " borrowed on " + hold.getBorrowedDate()
                            + " was due on " + hold.getDueDate() + ". \n"
                            + "We kindly request you to return the book to the Library.\n"
                            + "You will need to pay a fee regarding the late devolution.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.");

            emailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    @Override
    public void sendDueDateTomorrow(Hold hold) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_EMAIL);
            helper.setTo(hold.getUser().getEmail());
            helper.setSubject("Book Due Tomorrow.");
            helper.setText("Hello, " + hold.getUser().getName() + "!\n"
                            + "This is an informational email regarding the book borrowed from the Library.\n"
                            + "Please note that the volume of the "
                            + hold.getBookCopy().getBook().getTitle() + " - " + hold.getBookCopy().getBook().getAuthor()
                            + " borrowed on " + hold.getBorrowedDate() + " is due tomorrow.\n"
                            + "We kindly request you to return the book to the Library till the end of the day or you will be charged a fine.\n"
                            + "Thank you for your cooperation!\n"
                            + "Library Management.");

            emailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    
}
