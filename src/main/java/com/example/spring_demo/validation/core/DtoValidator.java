package com.example.spring_demo.validation.core;

import org.springframework.validation.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DtoValidator {

    Class<? extends Validator> customValidator() default DefaultValidator.class;

}
