package com.challenge.api.exceptions;

import com.challenge.api.payloads.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        GlobalErrorResponse response = new GlobalErrorResponse(
                false,
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<GlobalErrorResponse> handleInvalidRequest(InvalidRequestException ex) {
        GlobalErrorResponse response = new GlobalErrorResponse(
                false,
                ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<GlobalErrorResponse> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex) {
        GlobalErrorResponse response = new GlobalErrorResponse(
                false,
                ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        GlobalErrorResponse response = new GlobalErrorResponse(
                false,
                ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleGenericException(Exception ex) {
        GlobalErrorResponse response = new GlobalErrorResponse(
                false,
                "An unexpected error occurred: " + ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}