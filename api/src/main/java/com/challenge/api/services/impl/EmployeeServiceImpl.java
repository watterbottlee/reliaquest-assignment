package com.challenge.api.services.impl;

import com.challenge.api.exceptions.EmployeeAlreadyExistsException;
import com.challenge.api.exceptions.EmployeeNotFoundException;
import com.challenge.api.payloads.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.impl.EmployeeModel;
import com.challenge.api.services.EmployeeService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<UUID, Employee> store = new ConcurrentHashMap<>();

    public EmployeeServiceImpl() {
        seedMockData();
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("EmployeeServiceImpl->getAllEmployees() called");
        return new ArrayList<>(store.values());
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        log.info("EmployeeServiceImpl->getEmployeeByUUID() called");
        return Optional.ofNullable(store.get(uuid))
                .orElseThrow(() -> new EmployeeNotFoundException(uuid));
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest request) {
        if (store.values().stream()
                .anyMatch(emp -> emp.getEmail().equalsIgnoreCase(request.getEmail()))) {
            log.error("EmployeeServiceImpl -> createEmployee() -> employee with already exists with {}", request.getEmail());
            throw new EmployeeAlreadyExistsException(request.getEmail());
        }

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
        if (request.getContractTerminationDate() != null) {
            employee.setContractTerminationDate(request.getContractTerminationDate());
        }

        log.info("EmployeeServiceImpl -> createEmployee() -> employee object created");
        store.put(employee.getUuid(), employee);
        log.info("EmployeeServiceImpl -> createEmployee() -> employee stored");
        return employee;
    }

    private void seedMockData() {
        createEmployee(buildRequest("Kaniska Ranjan", "Barman", 500000, 21,
                "Associate Software Engineer", "kaniskaranjanbarman@gmail.com",
                Instant.parse("2024-01-15T09:00:00Z"), Instant.parse("2029-01-15T09:00:00Z")));

        createEmployee(buildRequest("Tiani", "Bello", 600000, 25,
                "HR Recruiter", "tbello@reliaquest.com",
                Instant.parse("2024-01-15T09:00:00Z"), Instant.parse("2029-01-15T09:00:00Z")));

        createEmployee(buildRequest("Relia", "Quest", 700000, 29,
                "Board Member", "support@reliaquest.com",
                Instant.parse("2024-01-15T09:00:00Z"), null));
    }

    private CreateEmployeeRequest buildRequest(
            String firstName, String lastName, Integer salary, Integer age,
            String title, String email, Instant contractHireDate, Instant contractTerminationDate) {
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