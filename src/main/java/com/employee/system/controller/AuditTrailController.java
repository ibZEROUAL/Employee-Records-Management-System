package com.employee.system.controller;

import com.employee.system.model.AuditTrail;
import com.employee.system.service.AuditTrailService;
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
    public List<AuditTrail> getAuditLogs(@PathVariable String id){
        return List.of();
    }
}
