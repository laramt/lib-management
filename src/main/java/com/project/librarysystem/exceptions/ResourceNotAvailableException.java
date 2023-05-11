package com.project.librarysystem.exceptions;

public class ResourceNotAvailableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
