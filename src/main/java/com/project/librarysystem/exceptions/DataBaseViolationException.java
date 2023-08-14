package com.project.librarysystem.exceptions;

public class DataBaseViolationException  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataBaseViolationException(String message) {
        super(message);
    }
    
}
