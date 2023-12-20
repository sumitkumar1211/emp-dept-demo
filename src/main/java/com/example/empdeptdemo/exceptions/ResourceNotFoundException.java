package com.example.empdeptdemo.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import static com.example.empdeptdemo.constant.AppConstants.COMMA_SPACE_DELIMITER;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String resourceName, final String fieldName, final Long fieldValue) {
        super(String.format("%s not found with %s = %d", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(final String resourceName, final String fieldName, final Set<Long> fieldValue) {
        super(String.format("%s not found with %s = %s", resourceName, fieldName, StringUtils.join(fieldValue, COMMA_SPACE_DELIMITER)));
    }
}
