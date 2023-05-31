package com.project.librarysystem.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.project.librarysystem.models.Patron;

@Data
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String emailFrom;
    private String emailTo;
    private Patron patron;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;

}
