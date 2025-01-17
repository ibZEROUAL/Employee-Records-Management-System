package com.employee.system.service;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.enums.Action;
import com.employee.system.exception.UserNotFoundException;
import com.employee.system.mapper.AuditTrailMapper;
import com.employee.system.model.AuditTrail;
import com.employee.system.model.Employee;
import com.employee.system.model.User;
import com.employee.system.repository.AuditTrailRepository;
import com.employee.system.repository.EmployeeRepository;
import com.employee.system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuditTrailService {

    private final AuditTrailRepository auditTrailRepository;

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    private final AuditTrailMapper auditTrailMapper;

    public List<AuditTrailDto> getAuditLogsByEmployeeId(Long employeeId){
        return auditTrailMapper.toDto(auditTrailRepository.getAuditTrailsByEmployeeId(employeeId));
    }

    public void saveAction(Long employeeId, Action action, String modifiedBy){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(UserNotFoundException::new);
        User user = userRepository.findByUsername(modifiedBy).orElseThrow(UserNotFoundException::new);
          AuditTrail auditTrail = new AuditTrail(null, employee, action, user, LocalDateTime.now());
          auditTrailRepository.save(auditTrail);
    }
}
