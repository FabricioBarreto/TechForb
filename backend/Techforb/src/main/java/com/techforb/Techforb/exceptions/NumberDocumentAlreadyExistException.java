package com.techforb.Techforb.exceptions;

public class NumberDocumentAlreadyExistException extends RuntimeException {

    public NumberDocumentAlreadyExistException() {
        super("User with this number document already exists");
    }

    public NumberDocumentAlreadyExistException(String message) {
        super(message);
    }

}
