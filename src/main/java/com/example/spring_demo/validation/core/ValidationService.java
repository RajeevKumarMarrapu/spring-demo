package com.example.spring_demo.validation.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ValidationService {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ValidationResultAnalyser validationResultAnalyser;

    public void validate(Object object, Class<? extends Validator> validatorClass) {
        BindingResult bindingResult = new BeanPropertyBindingResult(
                object, StringUtils.uncapitalize(object.getClass().getSimpleName()));
        Validator validator = applicationContext.getBean(validatorClass);
        ValidationUtils.invokeValidator(validator, object, bindingResult);
        validationResultAnalyser.analyseValidationResult(bindingResult);
    }
}
