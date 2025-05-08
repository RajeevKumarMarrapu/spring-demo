package com.example.spring_demo.validation.core;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public final class DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        throw new UnsupportedOperationException();
    }
}
