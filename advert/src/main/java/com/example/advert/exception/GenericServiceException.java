package com.example.advert.exception;

public class GenericServiceException extends RuntimeException {
    public static final String INTERNAL_ERROR = "internal_error";
    public static final String NOT_FOUND = "not_found";
    public static final String RESOURCE_NOT_FOUND = "resource_not_found";

    protected final String error;
    protected final String errorDescription;

    public GenericServiceException() {
        this.error = INTERNAL_ERROR;
        this.errorDescription = "Internal Error";
    }

    public GenericServiceException(String error, String errorDescription) {
        super(errorDescription);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public GenericServiceException(String error, String errorDescription, Throwable cause) {
        super(errorDescription, cause);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public GenericServiceException(String error, String errorDescription, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public GenericServiceException(String error, Throwable cause) {
        super(error, cause);
        this.error = error;
        this.errorDescription = "";
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
