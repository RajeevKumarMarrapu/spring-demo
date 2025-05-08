package com.example.spring_demo.validation.core;

import com.example.spring_demo.dto.ErrorDetails;
import com.example.spring_demo.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidationResultAnalyser {
    public void analyseValidationResult(Errors bindingResult) {
        List<ErrorDetails> errors = bindingResult.getFieldErrors()
                .stream()
                .map(this::buildErrorData)
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    protected ErrorDetails buildErrorData(FieldError error) {
        return ErrorDetails.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .description(error.getDefaultMessage())
                .details(List.of(error.getField(), error.getCode()))
                .shortCause(error.toString())
                .build();
    }

}
