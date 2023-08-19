package com.techforb.Techforb.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
