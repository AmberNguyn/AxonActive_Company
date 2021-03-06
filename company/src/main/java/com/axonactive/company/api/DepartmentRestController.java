package com.axonactive.company.api;

import com.axonactive.company.entity.Department;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.DepartmentRepository;
import com.axonactive.company.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(DepartmentRestController.PATH)
public class DepartmentRestController {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;
    public static final String PATH = "api/department";

    @GetMapping("")
    public List<Department> getAllDepartment() {
        List<Department> allDepartments = departmentService.getAll();
        return allDepartments;
    }

    //C - create
    @PostMapping("/add")
    public void saveDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }

    // R - retrieve
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found on: " + id));

        return ResponseEntity.ok().body(department);
    }

    //U - update
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department newDepartment, @PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Department department = departmentService.findDepartmentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        department.setDepartmentName(newDepartment.getDepartmentName());
        department.setStartDate(newDepartment.getStartDate());
        Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }


    //D - delete
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteDepartmentById(@PathVariable(value = "id") Integer id) throws Exception {
        Department department = departmentRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Department not found: " + id
                ));
        departmentService.deleteDepartment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;


    }


}
