package com.employee.system.dto;

import com.employee.system.enums.EmploymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private Long employeeId;
    private String fullName;
    private String jobTitle;
    private String department;
    private LocalDate hireDate;
    private EmploymentStatus employmentStatus;
    private String contactInfo;
    private String address;
}