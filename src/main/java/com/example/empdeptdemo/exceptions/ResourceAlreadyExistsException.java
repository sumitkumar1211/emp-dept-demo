package com.example.empdeptdemo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(final String resourceName, final String fieldName, final String fieldValue) {
        super(format("%s already exists with %s = %s", resourceName, fieldName, fieldValue));
    }
}
