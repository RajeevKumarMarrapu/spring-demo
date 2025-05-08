package com.example.spring_demo.exception;

public class EmployeeDataNotFoundException extends RuntimeException {
    public EmployeeDataNotFoundException(String message) {
        super(message);
    }
}
