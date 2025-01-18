package com.employee.system.controller;

import com.employee.system.dto.EmployeeDto;
import com.employee.system.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id){
        return ResponseEntity.ok(employeeService.getById(Long.valueOf(id)));
    }

    @GetMapping("/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<EmployeeDto>> getEmployeeById(@RequestParam(required = false) String id,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String department,
                                                       @RequestParam(required = false) String jobTitle){
        return ResponseEntity.ok(employeeService.searchEmployee(id, name, department, jobTitle));
    }

    @GetMapping("/filter")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<EmployeeDto>> filterEmployees(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDate) {

        return ResponseEntity.ok(employeeService.filterEmployees(status, department, hireDate));
    }

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto){
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    @PreAuthorize("@employeeSecurity.isManagerOfDepartment(authentication, #id)")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto, @PathVariable String id){
        var employeeDto = employeeService.updateEmployee(Long.valueOf(id),dto);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(Long.valueOf(id));
    }




}
