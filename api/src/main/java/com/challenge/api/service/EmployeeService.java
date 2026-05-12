package com.challenge.api.service;

import com.challenge.api.exceptions.EmployeeNotFoundException;
import com.challenge.api.payloads.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.implementations.EmployeeModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    private final Map<UUID, Employee> store = new ConcurrentHashMap<>();

    public EmployeeService() {
        seedMockData();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(store.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return Optional.ofNullable(store.get(uuid)).orElseThrow(()->new EmployeeNotFoundException(uuid));
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        EmployeeModel employee = new EmployeeModel();
        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(request.getFirstName() + " " + request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());
        if(request.getContractTerminationDate()!=null){
            employee.setContractTerminationDate(request.getContractTerminationDate());
        }
        store.put(employee.getUuid(), employee);
        return employee;
    }

    private void seedMockData() {
        createEmployee(buildRequest("Kaniska Ranjan",
                "Barman",
                500000, 21,
                "Associate Software Engineer",
                "kaniskaranjanbarman@gmail.com",
                Instant.parse("2024-01-15T09:00:00Z"),
                Instant.parse("2029-01-15T09:00:00Z")));

        createEmployee(buildRequest("Tiani",
                "Bello",
                600000,
                25,
                "HR Recruiter",
                "tbello@reliaquest.com",
                Instant.parse("2024-01-15T09:00:00Z"),
                Instant.parse("2029-01-15T09:00:00Z")));

        createEmployee(buildRequest("Relia",
                "Quest",
                700000,
                29,
                "Board Member",
                "support@reliaquest.com",
                Instant.parse("2024-01-15T09:00:00Z"),
                null));
    }

    private CreateEmployeeRequest buildRequest(
            String firstName,
            String lastName,
            Integer salary,
            Integer age,
            String title,
            String email,
            Instant contractHireDate,
            Instant contractTerminationDate) {
        CreateEmployeeRequest req = new CreateEmployeeRequest();
        req.setFirstName(firstName);
        req.setLastName(lastName);
        req.setSalary(salary);
        req.setAge(age);
        req.setJobTitle(title);
        req.setEmail(email);
        req.setContractHireDate(contractHireDate);
        req.setContractTerminationDate(contractTerminationDate);
        return req;
    }
}