package com.example.purchase.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e, WebRequest request) {
        return handleException(e, request, new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e));
    }

    @ExceptionHandler({ ResourceNotFountException.class })
    public ResponseEntity<?> handleEntityNotFound(ResourceNotFountException e, WebRequest request) {
        return handleException(e, request, new ApiError(HttpStatus.NOT_FOUND, e.getError(), e.getErrorDescription()));
    }

    @ExceptionHandler({ GenericServiceException.class })
    public ResponseEntity<?> handleGenericServiceException(GenericServiceException e, WebRequest request) {
        return handleException(e,  request,
                new ApiError(HttpStatus.CONFLICT, e.getError(), e.getErrorDescription()));
    }

    @ExceptionHandler({ AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(Exception e, WebRequest request) {
        return handleException(e,  request,
                new ApiError(HttpStatus.FORBIDDEN, "access_denied", "Access Denied"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<String> subErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> subErrors.add(e.getField() + ": " + e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(e -> subErrors.add(e.getObjectName() + ": " + e.getDefaultMessage()));

        return super.handleExceptionInternal(ex, new ApiError(HttpStatus.BAD_REQUEST, "invalid_request", subErrors),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleException(Exception e, WebRequest request, ApiError apiError) {
        log.error(e.getMessage(), e);
        return super.handleExceptionInternal(e, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }
}
