package com.techforb.Techforb.exceptions;

import com.techforb.Techforb.dto.response.ErrorResponse;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleTransactionException(TransactionException ex) {
        return new ErrorResponse(ex.getMessage());
    }

}