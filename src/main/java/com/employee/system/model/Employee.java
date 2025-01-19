package com.employee.system.model;

import com.employee.system.enums.Department;
import com.employee.system.enums.EmploymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
    @SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_seq", allocationSize = 1)
    private Long id;

    private String fullName;

    private String jobTitle;

    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String contactInfo;

    private String address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<AuditTrail> auditTrails;

    @Enumerated(EnumType.STRING)
    private Department department;


}
