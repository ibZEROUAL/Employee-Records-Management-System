package com.employee.system.service;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.enums.Action;
import com.employee.system.exception.EmployeeNotFoundException;
import com.employee.system.exception.UserNotFoundException;
import com.employee.system.mapper.AuditTrailMapper;
import com.employee.system.model.AuditTrail;
import com.employee.system.model.Employee;
import com.employee.system.model.User;
import com.employee.system.repository.AuditTrailRepository;
import com.employee.system.repository.EmployeeRepository;
import com.employee.system.repository.UserRepository;
import com.employee.system.service.util.ExportUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuditTrailService {

    private final AuditTrailRepository auditTrailRepository;

    private final UserRepository userRepository;

    private final AuditTrailMapper auditTrailMapper;

    private final EmployeeRepository employeeRepository;

    public List<AuditTrailDto> getAuditLogsByEmployeeId(Long employeeId){
        return auditTrailMapper.toDto(auditTrailRepository.getAuditTrailsByEmployeeId(employeeId));
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    public void deleteLogsByEmployeeId(Long employeeId){
        auditTrailRepository.deleteAuditTrailsByEmployee_Id(employeeId);
    }


    public ByteArrayInputStream export(Long id) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String sheetName;
        String[] headers;
        String title;

        if (id != null) {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(EmployeeNotFoundException::new);

            headers = new String[]{"Action", "Responsable", "Date", "Modified By"};
            sheetName = String.format("AuditLogs_%s", employee.getFullName().replace(" ", "_"));
            title = String.format("Audit Logs for %s", employee.getFullName());
        } else {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        Sheet sheet = workbook.createSheet(sheetName);
        ExportUtil.addMetadata(workbook, sheet, headers.length, title);
        ExportUtil.addHeaders(workbook, sheet, headers);

        List<AuditTrail> auditTrails = auditTrailRepository.getAuditTrailsByEmployeeId(id);

        AtomicInteger rowIdx = new AtomicInteger(6);
        auditTrails.forEach(auditTrail -> {
            Row row = sheet.createRow(rowIdx.getAndIncrement());
            int cellIdx = 0;

            row.createCell(cellIdx++).setCellValue(auditTrail.getAction().name());

            row.createCell(cellIdx++).setCellValue(auditTrail.getEmployee().getFullName());

            row.createCell(cellIdx++).setCellValue(
                    auditTrail.getModifiedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            );

            row.createCell(cellIdx).setCellValue(auditTrail.getModifiedBy().getUsername());
        });

        IntStream.range(0, headers.length).forEach(sheet::autoSizeColumn);

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

}
