package com.example.empdeptdemo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import static com.example.empdeptdemo.constant.AppConstants.COMMA_SPACE_DELIMITER;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.join;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String resourceName, final String fieldName, final Long fieldValue) {
        super(format("%s not found with %s = %d", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(final String resourceName, final String fieldName, final Set<Long> fieldValue) {
        super(format("%s not found with %s = %s", resourceName, fieldName, join(fieldValue, COMMA_SPACE_DELIMITER)));
    }
}
