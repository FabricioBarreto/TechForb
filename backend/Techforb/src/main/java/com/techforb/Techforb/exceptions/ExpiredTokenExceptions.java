package com.techforb.Techforb.exceptions;

public class ExpiredTokenExceptions extends RuntimeException {

    public ExpiredTokenExceptions() {
        super("The token has expired");
    }

    public ExpiredTokenExceptions(String message) {
        super(message);
    }

}
