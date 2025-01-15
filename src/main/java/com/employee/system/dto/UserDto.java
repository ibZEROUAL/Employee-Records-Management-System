package com.employee.system.dto;

import com.employee.system.enums.Role;

import java.io.Serializable;


public record UserDto(Long id, String username, Role role) implements Serializable {
}