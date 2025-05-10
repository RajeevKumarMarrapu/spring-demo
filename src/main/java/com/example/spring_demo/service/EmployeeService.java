package com.example.spring_demo.service;

import com.example.spring_demo.dto.EmployeeDto;
import com.example.spring_demo.exception.EmployeeDataNotFoundException;
import com.example.spring_demo.mapper.EmployeeMapper;
import com.example.spring_demo.repository.EmployeeRepository;
import com.example.spring_demo.repository.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private EmployeeMapper employeeMapper;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
//        if (isEmployeeExists(employeeDto.getId())) {
//            throw new EmployeeRecordAlreadyExistException("Employee record with id: " + employeeDto.getId() + " already exist");
//        }

        Employee result = employeeRepository.save(employeeMapper.fromEmployeeDto(employeeDto));
        log.info("Employee record created successfully with id {}", result.getId());
        return employeeMapper.toEmployeeDto(result);
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        if (!isEmployeeExists(employeeDto.getId())) {
            throw new EmployeeDataNotFoundException("Employee details not found with id: " + employeeDto.getId());
        }

        Employee result = employeeRepository.save(employeeMapper.fromEmployeeDto(employeeDto));
        log.info("Employee record updated successfully for id {}", result.getId());
        return employeeMapper.toEmployeeDto(result);
    }

    public void deleteEmployee(Long employeeId) {
        if (!isEmployeeExists(employeeId)) {
            throw new EmployeeDataNotFoundException("Employee details not found with id: " + employeeId);
        }

        employeeRepository.deleteById(employeeId);
        log.info("Employee record deleted successfully for id {}", employeeId);
    }

    public EmployeeDto getEmployee(Long employeeId) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if (byId.isEmpty()) {
            throw new EmployeeDataNotFoundException("Employee details not found with id: " + employeeId);
        }

        return employeeMapper.toEmployeeDto(byId.get());
    }

    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> results = new LinkedList<>();
        employeeRepository.findAll()
                .forEach(e -> results.add(employeeMapper.toEmployeeDto(e)));
        return results;
    }

    public List<EmployeeDto> getEmployees(int pageNumber, int size) {
        Page<Employee> result = employeeRepository.findAll(PageRequest.of(pageNumber, size, Sort.unsorted()));
        return result.stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }

    public boolean isEmployeeExists(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }
}
