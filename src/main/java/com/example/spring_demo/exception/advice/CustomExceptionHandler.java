package com.example.spring_demo.exception.advice;

import com.example.spring_demo.dto.ErrorDetails;
import com.example.spring_demo.exception.EmployeeDataNotFoundException;
import com.example.spring_demo.exception.EmployeeRecordAlreadyExistException;
import com.example.spring_demo.exception.ValidationException;
import jakarta.servlet.ServletException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EmployeeDataNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFound(EmployeeDataNotFoundException ex) {
        return ResponseEntity.badRequest()
                        .body(
                                ErrorDetails.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .description(ex.getMessage())
                                        .shortCause(ex.toString())
                                        .build()
                        );
    }

    @ExceptionHandler(EmployeeRecordAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateEmployeeRecord(EmployeeRecordAlreadyExistException ex) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorDetails.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .description(ex.getMessage())
                                .shortCause(ex.toString())
                                .build()
                );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorDetails>> handleDuplicateEmployeeRecord(ValidationException ex) {
        return ResponseEntity.badRequest()
                .body(
                        ex.getErrors()
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> hanldeAnyRuntimeExceptions(RuntimeException ex) {
        return ResponseEntity.internalServerError()
                .body(
                        ErrorDetails.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .description(ex.getMessage())
                                .shortCause(ex.toString())
                                .build()
                );
    }
}
