package com.sda.company.controller;

import com.sda.company.exceptions.EntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityException.class)
    public ResponseEntity<Object> handleCompanyException(EntityException entityException) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Timestamp", LocalDateTime.now());
        responseBody.put("Error message", entityException.getLocalizedMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
