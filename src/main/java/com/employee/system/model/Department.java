package com.employee.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dep_seq_gen")
    @SequenceGenerator(name = "dep_seq_gen", sequenceName = "dep_seq", allocationSize = 1)
    private Long id;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @ManyToOne
    private User manager;

    public Department(String department) {
        this.departmentName = department;
    }
}
