package com.example.empdeptdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(final ResourceNotFoundException ex, final WebRequest req) {
        return generateResponse(ex, req, NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceAlreadyExistsException(final ResourceAlreadyExistsException ex, final WebRequest req) {
        return generateResponse(ex, req, CONFLICT);
    }

    @ExceptionHandler(ResourceOperationNotAllowedException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceUpdateNotAllowedException(final ResourceOperationNotAllowedException ex, final WebRequest req) {
        return generateResponse(ex, req, METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(final IllegalArgumentException ex, final WebRequest req) {
        return generateResponse(ex, req, CONFLICT);
    }

    private ResponseEntity<CustomErrorResponse> generateResponse(final RuntimeException ex, final WebRequest req, final HttpStatus status) {
        final CustomErrorResponse res = CustomErrorResponse.builder()
                .date(new Date())
                .msg(ex.getMessage())
                .details(req.getDescription(false))
                .build();
        return ResponseEntity
                .status(status)
                .body(res);
    }
}
