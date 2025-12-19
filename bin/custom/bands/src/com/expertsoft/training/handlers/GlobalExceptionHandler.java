package com.expertsoft.training.handlers;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, AmbiguousIdentifierException.class, InterceptorException.class})
    private String handleBadRequest(Exception ex, ModelMap model) {
        model.addAttribute("message", ex.getMessage());
        return "400";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnknownIdentifierException.class)
    private String handleNotFound() {
        return "404";
    }
}
