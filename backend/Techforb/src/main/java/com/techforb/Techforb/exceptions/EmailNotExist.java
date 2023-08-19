package com.techforb.Techforb.exceptions;

public class EmailNotExist extends RuntimeException{

    public EmailNotExist() {
        super("Email not exist");
    }

    public EmailNotExist(String message) {
        super(message);
    }

}
