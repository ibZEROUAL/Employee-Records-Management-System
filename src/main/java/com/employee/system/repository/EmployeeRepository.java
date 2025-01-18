package com.employee.system.repository;

import com.employee.system.enums.EmploymentStatus;
import com.employee.system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeesByFullName(String fullName);

    List<Employee> findEmployeesByDepartment(String department);

    List<Employee> findEmployeesByJobTitle(String jobTitle);

    @Query("SELECT e FROM Employee e " +
            "WHERE (:status IS NULL OR e.employmentStatus = :status) " +
            "AND (:department IS NULL OR e.department = :department) " +
            "AND (:hireDate IS NULL OR e.hireDate >= :hireDate) ")
    List<Employee> filterEmployees(
            @Param("status") EmploymentStatus status,
            @Param("department") String department,
            @Param("hireDate") LocalDate hireDate);
}