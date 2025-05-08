package com.example.spring_demo.validation;

import com.example.spring_demo.dto.EmployeeDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) target;

        if (StringUtils.isEmpty(employeeDto.getName())) {
            errors.rejectValue("name", "Employee Name cannot be null or empty");
        }

        if (StringUtils.isEmpty(employeeDto.getRole())) {
            errors.rejectValue("role", "Employee Role cannot be null or empty");
        }
    }
}
