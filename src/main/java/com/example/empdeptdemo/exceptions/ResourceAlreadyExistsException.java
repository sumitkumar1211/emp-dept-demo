package com.example.empdeptdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(final String resourceName, final String fieldName, final String fieldValue) {
        super(String.format("%s already exists with %s = %s", resourceName, fieldName, fieldValue));
    }
}
