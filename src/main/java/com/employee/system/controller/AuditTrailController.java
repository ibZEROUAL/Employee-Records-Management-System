package com.employee.system.controller;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.service.AuditTrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/audit-trails")
@AllArgsConstructor
@CrossOrigin("*")
public class AuditTrailController {

    private AuditTrailService auditTrailService;

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<AuditTrailDto>> getAuditLogsByEmployeeId(@PathVariable String id){
        return ResponseEntity.ok(auditTrailService.getAuditLogsByEmployeeId(Long.valueOf(id)));
    }

    @GetMapping("/export/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Resource> export(@PathVariable String id) throws IOException {
        String filename = "AuditTrails.xlsx";
        Resource file = new InputStreamResource(auditTrailService.export(Long.valueOf(id)));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel\n"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .body(file);
    }
}
