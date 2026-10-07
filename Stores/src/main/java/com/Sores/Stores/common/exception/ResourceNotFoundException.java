package com.Sores.Stores.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String entityName, Long id) {
        super(entityName + " not found with id: " + id, HttpStatus.NOT_FOUND, "NOT_FOUND");

    }

}
