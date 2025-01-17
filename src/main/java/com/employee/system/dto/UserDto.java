package com.employee.system.dto;

import com.employee.system.enums.Role;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    Long id;
    String username;
    Role role;
}