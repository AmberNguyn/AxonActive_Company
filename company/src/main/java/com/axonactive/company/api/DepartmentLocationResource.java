package com.axonactive.company.api;

import com.axonactive.company.entity.DepartmentLocation;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.DepartmentLocationRepository;
import com.axonactive.company.service.DepartmentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departmentlocations")
public class DepartmentLocationResource {
    private final DepartmentLocationService departmentLocationService;
    private final DepartmentLocationRepository departmentLocationRepository;

    @GetMapping("/list")
    public List<DepartmentLocation> getAllDepartmentLocation() {
        List<DepartmentLocation> allDepartmentLocations = departmentLocationService.getAll();
        return allDepartmentLocations;
    }

    //C - create
    @PostMapping("/add")
    public void addDepartmentLocation(@RequestBody DepartmentLocation departmentLocation) {
        departmentLocationService.saveDepartmentLocation(departmentLocation);
        System.out.println("Successfully added!");
    }

    //R - retrieve
    @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentLocation> getDepartmentLocationById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        DepartmentLocation departmentLocation = departmentLocationService.findDepartmentLocationById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found: " + id)
                );
        return ResponseEntity.ok().body(departmentLocation);
    }

    //U - update

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentLocation> updateDepartmentLocation(@PathVariable(value = "id") Integer id,
                                                                       @RequestBody DepartmentLocation newDepartmentLocation)
            throws ResourceNotFoundException {
        DepartmentLocation departmentLocation = departmentLocationService.findDepartmentLocationById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Id not found" + id)
                );
        departmentLocation.setLocation(newDepartmentLocation.getLocation());
        departmentLocation.setDepartment(newDepartmentLocation.getDepartment());
        DepartmentLocation updatedDepartmentLocation = departmentLocationRepository.save(departmentLocation);
        return ResponseEntity.ok(updatedDepartmentLocation);
    }


    //D - delete
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteDepartmentLocationById(@PathVariable(value = "id") Integer id) throws Exception {
        DepartmentLocation departmentLocation = departmentLocationService.findDepartmentLocationById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found: " + id)
                );
        departmentLocationRepository.delete(departmentLocation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
