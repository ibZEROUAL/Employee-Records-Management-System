package com.employee.system.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
