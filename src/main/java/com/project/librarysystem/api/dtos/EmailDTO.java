package com.project.librarysystem.api.dtos;

import com.project.librarysystem.domain.models.Patron;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
