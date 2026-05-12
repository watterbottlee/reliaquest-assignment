package com.challenge.api.exceptions;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(UUID uuid) {
        super("Employee not found with UUID: " + uuid);
    }
}
