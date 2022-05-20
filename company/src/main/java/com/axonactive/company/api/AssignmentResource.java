package com.axonactive.company.api;

import com.axonactive.company.entity.Assignment;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AssignmentResource {
    private final AssignmentService assignmentService;

    @GetMapping("/assignments")
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
    @GetMapping("/get/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Assignment assignment = assignmentService.findAssignmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Assignment not found: " + id)
                );
        return ResponseEntity.ok().body(assignment);
    }

    //U - update

    //D - delete
    @DeleteMapping("delete/{id}")
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
