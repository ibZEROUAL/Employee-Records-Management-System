package com.employee.system.service;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.enums.Action;
import com.employee.system.exception.UserNotFoundException;
import com.employee.system.mapper.AuditTrailMapper;
import com.employee.system.model.AuditTrail;
import com.employee.system.model.Employee;
import com.employee.system.model.User;
import com.employee.system.repository.AuditTrailRepository;
import com.employee.system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuditTrailService {

    private final AuditTrailRepository auditTrailRepository;

    private final UserRepository userRepository;

    private final AuditTrailMapper auditTrailMapper;

    public List<AuditTrailDto> getAuditLogsByEmployeeId(Long employeeId){
        return auditTrailMapper.toDto(auditTrailRepository.getAuditTrailsByEmployeeId(employeeId));
    }

    public void saveLogAction(Employee employee, Action action){
        String modifiedBy = getAuthenticatedUsername();
        User user = userRepository.findByUsername(modifiedBy).orElseThrow(UserNotFoundException::new);
        AuditTrail auditTrail = new AuditTrail(null, employee, action, user, LocalDateTime.now());
        auditTrailRepository.save(auditTrail);
    }


    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
