package com.employee.system.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.employee.system.dto.EmployeeDto;
import com.employee.system.enums.Action;
import com.employee.system.enums.Department;
import com.employee.system.enums.EmploymentStatus;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.model.Employee;
import com.employee.system.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;
import java.util.Optional;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private AuditTrailService auditTrailService;

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository, employeeMapper, auditTrailService);
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDto);

        EmployeeDto result = employeeService.getById(id);

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toDto(employee);
    }

    @Test
    public void testGetAll() {

        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        List<EmployeeDto> employeeDtos = Arrays.asList(new EmployeeDto(), new EmployeeDto());
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.toDtoList(employees)).thenReturn(employeeDtos);

        List<EmployeeDto> result = employeeService.getAll();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).toDtoList(employees);
    }

    @Test
    public void testAddEmployee() {

        EmployeeDto dto = new EmployeeDto();
        dto.setDepartment(Department.IT);
        Employee employee = new Employee();
        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeMapper.toEntity(dto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.addEmployee(dto);

        assertNotNull(result);
        verify(employeeRepository, times(1)).save(employee);
        verify(auditTrailService, times(1)).saveLogAction(employee, Action.CREATED);
    }

    @Test
    public void testUpdateEmployee() {

        Long id = 1L;
        EmployeeDto dto = new EmployeeDto();
        Employee employee = new Employee();
        Employee updatedEmployee = new Employee();
        EmployeeDto updatedEmployeeDto = new EmployeeDto();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.partialUpdate(dto, employee)).thenReturn(updatedEmployee);
        when(employeeMapper.toDto(updatedEmployee)).thenReturn(updatedEmployeeDto);

        EmployeeDto result = employeeService.updateEmployee(id, dto);

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).partialUpdate(dto, employee);
        verify(auditTrailService, times(1)).saveLogAction(updatedEmployee, Action.UPDATED);
    }

    @Test
    public void testDeleteEmployee() {
        Long id = 1L;
        Employee employee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(id);

        verify(employeeRepository, times(1)).findById(id);
        verify(auditTrailService, times(1)).saveLogAction(employee, Action.DELETED);
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    public void testSearchEmployeeById() {
        Long id = 1L;
        Employee employee = new Employee();
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        List<EmployeeDto> result = employeeService.searchEmployee(id.toString(), null, null, null);

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toDto(employee);
    }

    @Test
    public void testSearchEmployeeByName() {
        String name = "John";
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        List<EmployeeDto> employeeDtos = Arrays.asList(new EmployeeDto(), new EmployeeDto());
        when(employeeRepository.findEmployeesByFullName(name)).thenReturn(employees);
        when(employeeMapper.toDtoList(employees)).thenReturn(employeeDtos);

        List<EmployeeDto> result = employeeService.searchEmployee(null, name, null, null);

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findEmployeesByFullName(name);
        verify(employeeMapper, times(1)).toDtoList(employees);
    }

    @Test
    public void testFilterEmployees() {
        String status = "ACTIVE";
        EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        List<EmployeeDto> employeeDtos = Arrays.asList(new EmployeeDto(), new EmployeeDto());
        when(employeeRepository.filterEmployees(employmentStatus, "IT", null)).thenReturn(employees);
        when(employeeMapper.toDtoList(employees)).thenReturn(employeeDtos);

        List<EmployeeDto> result = employeeService.filterEmployees(status, "IT", null);

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).filterEmployees(employmentStatus, "IT", null);
        verify(employeeMapper, times(1)).toDtoList(employees);
    }
}
