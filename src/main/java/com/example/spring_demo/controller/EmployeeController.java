package com.example.spring_demo.controller;

import com.example.spring_demo.dto.EmployeeDto;
import com.example.spring_demo.service.EmployeeService;
import com.example.spring_demo.validation.EmployeeValidator;
import com.example.spring_demo.validation.core.DtoValidator;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired private EmployeeService employeeService;

    @PostMapping
    public EmployeeDto createRecord(
            @DtoValidator(customValidator = EmployeeValidator.class)
            @RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateRecord(
            @DtoValidator(customValidator = EmployeeValidator.class)
            @RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteRecord(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/list")
    public List<EmployeeDto> getEmployeesBy(@PathParam("pageNumber") Integer pageNumber,
                                            @PathParam("pageLimit") Integer pageLimit) {
        return employeeService.getEmployees(pageNumber, pageLimit);
    }
}
