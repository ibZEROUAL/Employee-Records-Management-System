package com.employee.system.controller;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.service.AuditTrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-trails")
@AllArgsConstructor
@CrossOrigin("*")
public class AuditTrailController {

    private AuditTrailService auditTrailService;

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public List<AuditTrailDto> getAuditLogsByEmployeeId(@PathVariable String id){
        return auditTrailService.getAuditLogsByEmployeeId(Long.valueOf(id));
    }
}
