package com.axonactive.company.service;

import com.axonactive.company.entity.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    List<Assignment> getAll();

    //C - create
    public void saveAssignment(Assignment assignment);

    //R - retrieve
    public Optional<Assignment> findAssignmentById(Integer id);
    //U - update

    //D - delete
    public void deleteAssignment(Integer id);
}
