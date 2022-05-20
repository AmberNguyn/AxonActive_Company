package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Assignment;
import com.axonactive.company.service.AssignmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AssignmentServiceImplTest {
    @Autowired
    AssignmentService assignmentService;

    @Test
    void getAllAssignment_returnNoData_WhenJustCreateTable()
    {
        List<Assignment> assignments = assignmentService.getAll();
        assertEquals(0, assignments.size());
    }

    @Test
    void saveAssignment_returnOneData_whenAddOneAssignment()
    {
        Assignment assignment = Assignment.builder()
                .numberOfHour(3)
                .build();
        assignmentService.saveAssignment(assignment);
        List<Assignment> assignments = assignmentService.getAll();
        assertEquals(1, assignments.size());
    }

    @Test
    void deleteAssignment_returnZero_whenDeleteOneAssignmentFromAListOfOne()
    {
        Assignment assignment = Assignment.builder()
                .numberOfHour(3)
                .build();
        assignmentService.saveAssignment(assignment);
        assignmentService.deleteAssignment(1);
        List<Assignment> assignments = assignmentService.getAll();
        assertEquals(0, assignments.size());
    }

    @Test
    void testFindAssignmentById_ShouldReturnAnAssignment_whenInputAnAssignmentId()
    {
        Assignment assignment = Assignment.builder()
                .numberOfHour(3)
                .build();
        assignmentService.saveAssignment(assignment);
        assertEquals(assignment, assignmentService.findAssignmentById(1).get());
    }


}