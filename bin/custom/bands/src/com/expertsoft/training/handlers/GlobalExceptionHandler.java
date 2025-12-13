package com.expertsoft.training.handlers;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, AmbiguousIdentifierException.class})
    private String handleBadRequest() {
        return "400";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnknownIdentifierException.class)
    private String handleNotFound() {
        return "404";
    }
}
