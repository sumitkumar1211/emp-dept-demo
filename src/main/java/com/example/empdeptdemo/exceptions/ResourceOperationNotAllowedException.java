package com.example.empdeptdemo.exceptions;

import com.example.empdeptdemo.constant.AppOperation;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@ResponseStatus(METHOD_NOT_ALLOWED)
public class ResourceOperationNotAllowedException extends RuntimeException {
    public ResourceOperationNotAllowedException(final AppOperation operation, final String resourceName,
                                                final String fieldName, final boolean fieldValue) {
        super(format("%s operation not allowed for %s with %s = %b", operation, resourceName, fieldName, fieldValue));
    }

    public ResourceOperationNotAllowedException(final AppOperation operation, final String resourceName,
                                                final String fieldName, final Long fieldValue) {
        super(format("%s operation not allowed for %s with %s = %d", operation, resourceName, fieldName, fieldValue));
    }
}
