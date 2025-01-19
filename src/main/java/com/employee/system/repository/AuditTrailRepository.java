package com.employee.system.repository;

import com.employee.system.model.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {

    List<AuditTrail> getAuditTrailsByEmployeeId(Long employeeId);

    void deleteAuditTrailsByEmployee_Id(Long employeeId);
}