package com.employee.system.service;

import com.employee.system.dto.EmployeeDto;
import com.employee.system.exception.EmployeeNotFoundException;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    public EmployeeDto getById(Long id) {
        var employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.toDto(employee);
    }

    public List<EmployeeDto> getAll(){
        System.out.println("//////////////");
        employeeRepository.findAll().forEach(System.out::println);
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    public EmployeeDto addEmployee(EmployeeDto dto){
        var employee = employeeMapper.toEntity(dto);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){
        var employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        var updatedEmployee = employeeMapper.partialUpdate(dto,employee);
        return employeeMapper.toDto(employeeRepository.save(updatedEmployee));
    }

    public void deleteEmployee(Long id){
        employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.deleteById(id);
    }


}
