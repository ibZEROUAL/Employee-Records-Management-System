package com.employee.system.service.util;

import com.employee.system.exception.EmployeeNotFoundException;
import com.employee.system.exception.UserNotFoundException;
import com.employee.system.model.Department;
import com.employee.system.model.Employee;
import com.employee.system.model.User;
import com.employee.system.repository.EmployeeRepository;
import com.employee.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSecurity {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean isManagerOfDepartment(Authentication authentication, Long employeeId) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Department managerDepartment = user.getDepartment();

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Department employeeDepartment = employee.getDepartment();

        return managerDepartment.equals(employeeDepartment);
    }
}