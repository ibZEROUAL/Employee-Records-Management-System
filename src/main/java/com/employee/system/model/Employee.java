package com.employee.system.model;

import com.employee.system.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String jobTitle;

    private String department;

    private LocalDate hireDate;

    private EmploymentStatus employmentStatus;

    private String contactInfo;

    private String address;


}
