package com.axonactive.company.api;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.EmployeeRepository;
import com.axonactive.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAll();
        return allEmployees;
    }

    //C - create
    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        System.out.println("Successfully added");
    }

    //U - update

    //R - retrieve
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not found: " + id)
                );
        return ResponseEntity.ok().body(employee);
    }


    //D - delete
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteEmployeeById(@PathVariable(value = "id") Integer id) throws Exception {
        Employee employee = employeeService.findEmployeeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not found: " + id)
                );
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
