package com.challenge.api.controller;

import com.challenge.api.payloads.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.services.EmployeeService;
import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("EmployeeController -> getAllEmployees() called");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        log.info("EmployeeController -> getEmployeeByUuid() called");
        return employeeService.getEmployeeByUuid(uuid);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody @Valid CreateEmployeeRequest requestBody) {
        log.info("EmployeeController -> createEmployee() called");
        return employeeService.createEmployee(requestBody);
    }
}
