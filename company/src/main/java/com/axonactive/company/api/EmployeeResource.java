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
@RequestMapping("/employees")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/list")
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
    @PutMapping("update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody Employee newEmployee)
        throws ResourceNotFoundException
    {
        Employee employee = employeeService.findEmployeeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Id not found")
                );
        employee.setEmployeeIdFake(newEmployee.getEmployeeIdFake());
        employee.setDepartment(newEmployee.getDepartment());
        employee.setGender(newEmployee.getGender());
        employee.setFirstName(newEmployee.getFirstName());
        employee.setDateOfBirth(newEmployee.getDateOfBirth());
        employee.setLastName(newEmployee.getLastName());
        employee.setMiddleName(newEmployee.getMiddleName());
        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }






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
