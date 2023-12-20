package com.example.empdeptdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(final ResourceNotFoundException ex, final WebRequest req) {
        final CustomErrorResponse res = new CustomErrorResponse(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceAlreadyExistsException(final ResourceAlreadyExistsException ex, final WebRequest req) {
        final CustomErrorResponse res = new CustomErrorResponse(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceOperationNotAllowedException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceUpdateNotAllowedException(final ResourceOperationNotAllowedException ex, final WebRequest req) {
        final CustomErrorResponse res = new CustomErrorResponse(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
