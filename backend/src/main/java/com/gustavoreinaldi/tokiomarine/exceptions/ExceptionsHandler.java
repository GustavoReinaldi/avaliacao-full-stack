package com.gustavoreinaldi.tokiomarine.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { InvalidArgumentsException.class})
    protected ResponseEntity<Object> handleBadRequests(
            RuntimeException ex, WebRequest request) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status","NOK");
        responseBody.put("message", ex.getMessage());
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleBadRequests(
            Exception ex, WebRequest request) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status","NOK");
        responseBody.put("message", "An internal server has occurred. Please try again later.");
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
