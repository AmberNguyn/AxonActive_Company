package com.axonactive.company.api;

import com.axonactive.company.entity.Assignment;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.AssignmentRepository;
import com.axonactive.company.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(AssignmentRestController.PATH)
public class AssignmentRestController {
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;
    public static final String PATH = "api/assignments";

    @GetMapping
    public List<Assignment> getAllAssignment()
    {
        List<Assignment> allAssignments = assignmentService.getAll();
        return allAssignments;
    }

    //C - create
    @PostMapping("/add")
    public void addAssignment(@RequestBody Assignment assignment)
    {
        assignmentService.saveAssignment(assignment);
    }

    //R - retrieve
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Assignment assignment = assignmentService.findAssignmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Assignment not found: " + id)
                );
        return ResponseEntity.ok().body(assignment);
    }

    //U - update
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody Assignment newAssignment, @PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Assignment assignment = assignmentService.findAssignmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Id " + id + " not found")
                );
        assignment.setEmployee(newAssignment.getEmployee());
        assignment.setProject(newAssignment.getProject());
        assignment.setNumberOfHour(newAssignment.getNumberOfHour());
        Assignment updatedAssignment = assignmentRepository.save(assignment);

        return ResponseEntity.ok(updatedAssignment);
    }


    //D - delete
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAssignmentById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Assignment assignment = assignmentService.findAssignmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Assignment not found: " + id)
                );
        assignmentService.deleteAssignment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
