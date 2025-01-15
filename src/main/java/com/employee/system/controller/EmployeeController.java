package com.employee.system.controller;

import com.employee.system.dto.EmployeeDto;
import com.employee.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/{id}")
   // @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id){
        return ResponseEntity.ok(employeeService.getById(Long.valueOf(id)));
    }

    @GetMapping
  //  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

  //  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto){
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

   // @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto, @PathVariable String id){
        var employeeDto = employeeService.updateEmployee(Long.valueOf(id),dto);
        return ResponseEntity.ok(employeeDto);
    }

   // @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(Long.valueOf(id));
    }

}