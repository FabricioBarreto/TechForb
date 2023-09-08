package com.Techforb.Techforb.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class CardException extends RuntimeException{

    public CardException(String message, String card) {
            super(message);
        }

}
