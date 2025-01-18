package com.employee.system.dto;

import com.employee.system.enums.EmploymentStatus;
import com.employee.system.model.Department;
import jakarta.validation.constraints.*;
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

    @Null(message = "ID should be null for new employees")
    private Long id;

    @NotBlank
    @Email(message = "Username must be a valid email address")
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    private String jobTitle;

    @NotNull
    private Department department;

    @NotNull
    @PastOrPresent(message = "Hire date must be in the past or today")
    private LocalDate hireDate;

    @NotNull
    private EmploymentStatus employmentStatus;

    @NotBlank
    @Pattern(
            regexp = "^\\+?[0-9. ()-]{7,25}$"
    )
    private String contactInfo;

    @NotBlank
    private String address;

}