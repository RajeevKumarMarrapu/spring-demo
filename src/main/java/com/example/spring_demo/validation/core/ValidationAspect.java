package com.example.spring_demo.validation.core;

import com.example.spring_demo.exception.ValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Parameter;

@Configuration
@Aspect
public class ValidationAspect {
    private static final Logger LOG = LoggerFactory.getLogger(ValidationAspect.class);

    @Autowired
    private ValidationService validationService;


    @Around("execution(* com.example.spring_demo.controller..*.*(.., @com.example.spring_demo.validation.core.DtoValidator (*),..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        MethodSignature ms = (MethodSignature) point.getSignature();
        Parameter[] parameters = ms.getMethod().getParameters();

        LOG.debug("Handling validation for method {}", ms.getName());

        int exceptionHandlerIndex = getExceptionHandlerIndex(parameters);

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            DtoValidator dtoValidator = parameter.getDeclaredAnnotation(DtoValidator.class);
            if (dtoValidator == null) {
                continue;
            }

            try {
                validationService.validate(args[i], dtoValidator.customValidator());
            } catch (ValidationException e) {
                if (exceptionHandlerIndex != -1) {
                    args[exceptionHandlerIndex] = e;
                } else {
                    throw e;
                }
            }
        }

        return point.proceed();
    }

    private int getExceptionHandlerIndex(Parameter[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (ValidationException.class.isAssignableFrom(parameter.getType())) {
                return i;
            }
        }
        return -1;
    }

}
