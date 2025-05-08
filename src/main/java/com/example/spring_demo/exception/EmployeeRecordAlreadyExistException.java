package com.example.spring_demo.exception;

public class EmployeeRecordAlreadyExistException extends RuntimeException {
    public EmployeeRecordAlreadyExistException(String message) {
        super(message);
    }
}
