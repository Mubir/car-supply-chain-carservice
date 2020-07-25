package com.mubir.carservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> vErrorHandler(ConstraintViolationException e)
    {
        List<String> error = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constrainViolation -> {
            error.add(constrainViolation.getPropertyPath()+" : "+constrainViolation.getMessage());
        });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
