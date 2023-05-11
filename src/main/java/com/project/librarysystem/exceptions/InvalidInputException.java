package com.project.librarysystem.exceptions;

public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidInputException(String message) {
        super(message);
    }
}
