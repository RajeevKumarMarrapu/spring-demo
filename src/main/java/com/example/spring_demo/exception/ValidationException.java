package com.example.spring_demo.exception;

import com.example.spring_demo.dto.ErrorDetails;

import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException {
	private final List<ErrorDetails> errors;

    public ValidationException(ErrorDetails errorData) {
        this.errors = Collections.singletonList(errorData);
    }

    public ValidationException(List<ErrorDetails> data) {
        this.errors = data;
    }

    public List<ErrorDetails> getErrors() {
        return errors;
    }
}
