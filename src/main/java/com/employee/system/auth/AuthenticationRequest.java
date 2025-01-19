package com.employee.system.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {


    @NotBlank
    @Email(message = "Username must be a valid email address")
    private String username;

    private String password;

}
