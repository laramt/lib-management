package com.project.librarysystem.services;

import com.project.librarysystem.models.Loan;


public interface EmailService {

    void sendBorrowedBook(Loan hold);
    void sendLateBook(Loan hold);
    void sendDueDateTomorrow(Loan hold);
    
}
