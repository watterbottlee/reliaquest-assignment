package com.challenge.api.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(String email){
        super("Employee already exists with email: "+email);
    }
}
