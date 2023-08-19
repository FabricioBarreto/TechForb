package com.techforb.Techforb.exceptions;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Invalid token");
    }

    public InvalidTokenException(String message) {
        super(message);
    }

}
