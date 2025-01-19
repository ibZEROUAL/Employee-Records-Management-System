package com.employee.system.auth;

import com.employee.system.enums.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String fullName;


    @NotBlank
    @Email(message = "Username must be a valid email address")
    private String username;

    private String password;

    private Department department;
}
