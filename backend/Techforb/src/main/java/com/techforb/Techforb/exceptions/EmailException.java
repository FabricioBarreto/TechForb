package com.Techforb.Techforb.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}
