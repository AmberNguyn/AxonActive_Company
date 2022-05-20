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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DepartmentResource {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/department")
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
    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@RequestParam Integer id) throws ResourceNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found on: " + id));

        return ResponseEntity.ok().body(department);
    }

    //U - update

    //D - delete
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteDepartmentById(@RequestParam Integer id) throws Exception {
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
