package com.example.payment.exception;

public class ResourceNotFountException extends GenericServiceException {
    public ResourceNotFountException() {
        super(RESOURCE_NOT_FOUND, "Requested resource not found");
    }

    public ResourceNotFountException(String errorDescription) {
        super(RESOURCE_NOT_FOUND, errorDescription);
    }
}
