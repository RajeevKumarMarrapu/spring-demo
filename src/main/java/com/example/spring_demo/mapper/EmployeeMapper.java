package com.example.spring_demo.mapper;

import com.example.spring_demo.dto.EmployeeDto;
import com.example.spring_demo.repository.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);
    Employee fromEmployeeDto(EmployeeDto employee);
}
