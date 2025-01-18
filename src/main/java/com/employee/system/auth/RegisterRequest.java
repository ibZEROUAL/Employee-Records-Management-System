package com.employee.system.auth;

import com.employee.system.model.Department;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String fullName;

    private String username;

    private String password;

    private Department department;
}
