package com.challenge.api.services;

import com.challenge.api.model.Employee;
import com.challenge.api.payloads.CreateEmployeeRequest;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeByUuid(UUID uuid);

    Employee createEmployee(CreateEmployeeRequest request);
}
