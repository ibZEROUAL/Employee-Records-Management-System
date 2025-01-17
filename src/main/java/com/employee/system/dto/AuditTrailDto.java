package com.employee.system.dto;

import com.employee.system.enums.Action;

import java.io.Serializable;
import java.time.LocalDateTime;


public record AuditTrailDto(Long id, EmployeeDto employee, Action action, UserDto modifiedBy,
                            LocalDateTime modifiedAt) implements Serializable {
}