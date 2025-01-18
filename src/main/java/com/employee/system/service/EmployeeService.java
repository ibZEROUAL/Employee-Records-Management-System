package com.employee.system.service;

import com.employee.system.dto.EmployeeDto;
import com.employee.system.enums.Action;
import com.employee.system.enums.EmploymentStatus;
import com.employee.system.exception.EmployeeNotFoundException;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final AuditTrailService auditTrailService;



    public EmployeeDto getById(Long id) {
        var employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAll(){
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    public EmployeeDto addEmployee(EmployeeDto dto){
        var employee = employeeMapper.toEntity(dto);
        var savedEmployee = employeeRepository.save(employee);
        auditTrailService.saveLogAction(savedEmployee, Action.CREATED);
        return employeeMapper.toDto(savedEmployee);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){
        var employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        var updatedEmployee = employeeMapper.partialUpdate(dto,employee);
        auditTrailService.saveLogAction(updatedEmployee, Action.UPDATED);
        return employeeMapper.toDto(updatedEmployee);
    }

    public void deleteEmployee(Long id){
       var employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        auditTrailService.saveLogAction(employee, Action.DELETED);
        employeeRepository.delete(employee);
    }


    public List<EmployeeDto> searchEmployee(String id, String name, String department, String jobTitle) {

        if (id != null) {
            return List.of(getById(Long.valueOf(id)));
        } else if (name != null) {
            return employeeMapper.toDtoList(employeeRepository.findEmployeesByFullName(name));
        } else if (department != null) {
            return employeeMapper.toDtoList(employeeRepository.findEmployeesByDepartment_DepartmentName(department));
        } else if (jobTitle != null) {
            return employeeMapper.toDtoList(employeeRepository.findEmployeesByJobTitle(jobTitle));
        }else

        return List.of();
    }

    public List<EmployeeDto> filterEmployees(String status, String department, LocalDate hireDate) {
        EmploymentStatus employmentStatus = null;
        if (status != null) {
            employmentStatus = EmploymentStatus.valueOf(status);
        }
        return employeeMapper.toDtoList(employeeRepository.filterEmployees(employmentStatus, department, hireDate));
    }
}
