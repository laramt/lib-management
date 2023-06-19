package com.project.librarysystem.services;

import com.project.librarysystem.models.Hold;


public interface EmailService {

    void sendBorrowedBook(Hold hold);
    void sendLateBook(Hold hold);
    void sendDueDateTomorrow(Hold hold);
    
}
